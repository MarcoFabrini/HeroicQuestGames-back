package com.hqc.beck.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqc.beck.dto.OrdersDTO;
import com.hqc.beck.model.Carts;
import com.hqc.beck.model.DetailsCart;
import com.hqc.beck.model.DetailsOrder;
import com.hqc.beck.model.Orders;
import com.hqc.beck.model.PayCards;
import com.hqc.beck.model.Users;
import com.hqc.beck.repository.ICartsRepository;
import com.hqc.beck.repository.IDetailsCartsRepository;
import com.hqc.beck.repository.IDetailsOrderRepository;
import com.hqc.beck.repository.IOrdersRepository;
import com.hqc.beck.repository.IPayCardsRepository;
import com.hqc.beck.repository.UsersRepository;
import com.hqc.beck.request.OrdersRequest;
import com.hqc.beck.services.interfaces.IOrdersService;
import static com.hqc.beck.utils.Utilities.buildDetailsOrderDTO;
import static com.hqc.beck.utils.Utilities.buildOrdersDTO;
import static com.hqc.beck.utils.Utilities.buildPayCardsDTO;

@Service
public class OrdersImplementation implements IOrdersService {

    @Autowired
    IOrdersRepository orderRep;

    @Autowired
    IDetailsOrderRepository detailsOrdersRep;

    @Autowired
    UsersRepository userRep;

    @Autowired
    IPayCardsRepository cardRep;

    @Autowired
    ICartsRepository cartRep;

    @Autowired
    IDetailsCartsRepository detailsCartRep;

    @Override
    public List<OrdersDTO> findAllOrders() throws Exception {
        List<Orders> listOrders = orderRep.findAll();
        return listOrders.stream()
                .map(order -> new OrdersDTO(
                        order.getId(),
                        order.getTotalAmmount(),
                        order.getOrderStatus(),
                        order.getCreatedAt(),
                        order.getUpdatedAt(),
                        buildDetailsOrderDTO(order.getListDetailsOrder()),
                        buildPayCardsDTO(order.getPayCard())))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrdersDTO> findByUser(Integer id) throws Exception {
        Optional<Users> user = userRep.findById(id);
        if (user.isEmpty()) {
            throw new Exception("user-noPresent");
        }
        return buildOrdersDTO(user.get().getListOrders());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(OrdersRequest req) throws Exception {
        Optional<Users> user = userRep.findById(req.getUserId());
        if (user.isEmpty()) {
            throw new Exception("user-noPresent");
        }

        Optional<Carts> carts = cartRep.findByUser(user.get());
        if (carts.isEmpty()) {
            throw new Exception("cart-noPresent");
        }

        List<DetailsCart> lDetailsCart = detailsCartRep.findByCart(carts.get());
        if (lDetailsCart.isEmpty()) {
            throw new Exception("cart-noItems");
        }

        Optional<PayCards> card = cardRep.findById(req.getPayCardId());
        if (card.isEmpty()) {
            throw new Exception("card-noPresent");
        }

        if (card.get().getExpirationDate().before(new java.util.Date())) {
            throw new Exception("card-expired");
        }

        Double totalAmount = lDetailsCart.stream()
                .map(x -> x.getProduct().getPrice() * x.getQuantity())
                .reduce(0.0, Double::sum);

        Orders ord = new Orders();
        ord.setTotalAmmount(totalAmount);
        ord.setOrderStatus("pending");
        ord.setUser(user.get());
        ord.setPayCard(card.get());

        Orders newOrd = orderRep.save(ord);

        lDetailsCart.forEach(x -> {
            DetailsOrder detailOrder = new DetailsOrder();
            detailOrder.setPriceAtTime(x.getProduct().getPrice() * x.getQuantity());
            detailOrder.setQuantity(x.getQuantity());
            detailOrder.setOrder(newOrd);
            detailOrder.setProduct(x.getProduct());
            detailsOrdersRep.save(detailOrder);
        });

        detailsCartRep.deleteAll(lDetailsCart);
    }

    @Override
    public void update(OrdersRequest req) throws Exception {
        Optional<Orders> order = orderRep.findById(req.getId());
        if (order.isEmpty()) {
            throw new Exception("order-noPresent");
        }

        Orders ord = order.get();
        ord.setOrderStatus(req.getOrderStatus());

        orderRep.save(ord);
    }
}
