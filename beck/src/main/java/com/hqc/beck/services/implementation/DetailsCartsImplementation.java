package com.hqc.beck.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqc.beck.dto.DetailsCartDTO;
import com.hqc.beck.model.Carts;
import com.hqc.beck.model.DetailsCart;
import com.hqc.beck.model.Product;
import com.hqc.beck.repository.ICartsRepository;
import com.hqc.beck.repository.IDetailsCartsRepository;
import com.hqc.beck.repository.IProductRepository;
import com.hqc.beck.repository.UsersRepository;
import com.hqc.beck.request.DetailsCartRequest;
import com.hqc.beck.services.interfaces.IDetailsCartsService;
import static com.hqc.beck.utils.Utilities.buildDetailsCartsDTO;

@Service
public class DetailsCartsImplementation implements IDetailsCartsService {

    @Autowired
    IDetailsCartsRepository detailsCartR;

    @Autowired
    ICartsRepository cartR;

    @Autowired
    IProductRepository productRepository;

    @Autowired
    UsersRepository usersR;

    @Autowired
    private Logger log;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(DetailsCartRequest req) throws Exception {
        Optional<Carts> carts = cartR.findById(req.getCartId());
        Optional<Product> product = productRepository.findById(req.getProductId());

        if (product.isEmpty())
            throw new Exception("game-id");

        if (detailsCartR.existsByCartAndProduct(carts.get(), product.get())) {
            throw new Exception("cartItem-present");
        }

        DetailsCart detailsCart = new DetailsCart();
        detailsCart.setCart(carts.get());
        detailsCart.setProduct(product.get());
        detailsCart.setQuantity(req.getQuantity());
        detailsCartR.save(detailsCart);
        cartR.save(carts.get());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(DetailsCartRequest req) throws Exception {
        Optional<DetailsCart> detailsCarts = detailsCartR.findById(req.getId());
        if (detailsCarts.isEmpty())
            throw new Exception("cartItem-noPresent");

        DetailsCart dC = detailsCarts.get();

        if (req.getQuantity() <= 0) {
            delete(req);
        }

        dC.setQuantity(req.getQuantity());
        cartR.save(dC.getCart());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(DetailsCartRequest req) throws Exception {
        Optional<DetailsCart> detailsCarts = detailsCartR.findById(req.getId());
        if (detailsCarts.isEmpty())
            throw new Exception("cartItem-noPresent");

        detailsCartR.delete(detailsCarts.get());
        cartR.save(detailsCarts.get().getCart());
    }

    @Override
    public void deleteAllByCart(DetailsCartRequest req) throws Exception {
        Optional<Carts> carts = cartR.findById(req.getCartId());
        if (carts.isEmpty())
            throw new Exception("cart-noPresent");

        List<DetailsCart> dCl = detailsCartR.findByCart(carts.get());
        detailsCartR.deleteAll(dCl);
    }

    @Override
    public List<DetailsCartDTO> list() throws Exception {
        return buildDetailsCartsDTO(detailsCartR.findAll());
    }

    @Override
    public List<DetailsCartDTO> listByCarts(Integer id) throws Exception {
        Optional<Carts> carts = cartR.findById(id);
        if (carts.isEmpty()) {
            throw new Exception("cart-noPresent");
        }
        return buildDetailsCartsDTO(detailsCartR.findByCart(carts.get()));
    }
}
