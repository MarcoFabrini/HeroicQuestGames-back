package com.hqc.beck.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqc.beck.dto.ProductDTO;
import com.hqc.beck.model.Editors;
import com.hqc.beck.model.Product;
import com.hqc.beck.repository.IAuthorsRepository;
import com.hqc.beck.repository.ICategoriesRepository;
import com.hqc.beck.repository.IEditorsRepository;
import com.hqc.beck.repository.IProductRepository;
import com.hqc.beck.repository.IReviewsRepository;
import com.hqc.beck.request.ProductRequest;
import com.hqc.beck.services.interfaces.IProductService;
import static com.hqc.beck.utils.Utilities.buildProductDTO;
import static com.hqc.beck.utils.Utilities.buildProductDTOid;

@Service
public class ProductImplementation implements IProductService {

    @Autowired
    IProductRepository productRepository;

    @Autowired
    IEditorsRepository editorsRepository;

    @Autowired
    IAuthorsRepository authorsRepository;

    @Autowired
    ICategoriesRepository categoriesRepository;

    @Autowired
    IReviewsRepository reviewsRepository;

    @Autowired
    Logger log;

    @Override
    public List<ProductDTO> list() throws Exception {
        List<Product> listProduct = productRepository.findAll();
        return buildProductDTO(listProduct);
    }// list

    @Override
    public ProductDTO getProductById(Integer id) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("game-noPresent"));

        return buildProductDTOid(product);
    }// geProductById

    

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProductRequest req) throws Exception {
        if (productRepository.findByNameAndIdNot(req.getName(), req.getId()).isPresent())
            throw new Exception("Product-Already-Present");

        Optional<Editors> editor = editorsRepository.findById(req.getEditorsId());
        if (!editor.isPresent())
            throw new Exception("Editor-Not-Found");

        Product g = new Product();
        g.setName(req.getName());
        g.setDescription(req.getDescription());
        g.setPrice(req.getPrice());
        g.setDate(req.getDate());
        g.setMinGameTime(req.getMinGameTime());
        g.setMaxGameTime(req.getMaxGameTime());
        g.setMinPlayerNumber(req.getMinPlayerNumber());
        g.setMaxPlayerNumber(req.getMaxPlayerNumber());
        g.setMinAge(req.getMinAge());
        g.setStockQuantity(req.getStockQuantity());
        g.setActive(true);

        g.setEditor(editor.get());

        productRepository.save(g);
    }// create

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProductRequest req) throws Exception {
        Optional<Product> product = productRepository.findById(req.getId());
        if (!product.isPresent()) {
            throw new Exception("game-isPresent");
        }

        Optional<Editors> editor = editorsRepository.findById(req.getEditorsId());

        Product p = product.get();
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setPrice(req.getPrice());
        p.setDate(req.getDate());
        p.setMinGameTime(req.getMinGameTime());
        p.setMaxGameTime(req.getMaxGameTime());
        p.setMinPlayerNumber(req.getMinPlayerNumber());
        p.setMaxPlayerNumber(req.getMaxPlayerNumber());
        p.setMinAge(req.getMinAge());
        p.setStockQuantity(req.getStockQuantity());
        p.setEditor(editor.get());
        p.setActive(req.getActive());

        // if (req.getAuthorsId() != null) {
        // Optional<Authors> a = authorsR.findById(req.getAuthorsId());
        // if (a.isPresent()) {
        // Authors author = a.get();
        // g.getListAuthors().add(author);
        // }
        // }

        // if (req.getCategoryId() != null) {
        // Optional<Categories> c = categoryR.findById(req.getCategoryId());
        // if (c.isPresent()) {
        // Categories category = c.get();
        // g.getListCategory().add(category);
        // }
        // }

        // save
        productRepository.save(p);

    }// update

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new Exception("game-noPresent");
        }

        // if (gameEntity.getListAuthors() != null) {
        // gameEntity.getListAuthors().forEach(author ->
        // author.getListGames().remove(game));
        // gameEntity.getListAuthors().clear();
        // }

        // if (gameEntity.getListCategory() != null) {
        // gameEntity.getListCategory().forEach(category ->
        // category.getListGames().remove(gameEntity));
        // gameEntity.getListCategory().clear();
        // }

        // if (gameEntity.getListReviews() != null) {
        // gameEntity.getListReviews().forEach(review -> review.setGame(null));
        // gameEntity.getListReviews().clear();
        // }

        product.get().setActive(false);

        productRepository.save(product.get());
    }// delete

    // @Override
    // public List<GamesDTO> searchByTyping(String name, Integer authorsId, Integer
    // categoriesId, Integer editorId)
    // throws Exception {
    // List<Games> listGames = gamesR.searchByTyping(name, authorsId, categoriesId,
    // editorId);
    // return buildGamesDTO(listGames);
    // }// searchByTyping
}// class
