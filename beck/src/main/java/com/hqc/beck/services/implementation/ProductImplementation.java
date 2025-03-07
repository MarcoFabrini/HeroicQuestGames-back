package com.hqc.beck.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqc.beck.dto.ProductDTO;
import com.hqc.beck.model.Authors;
import com.hqc.beck.model.Categories;
import com.hqc.beck.model.Editors;
import com.hqc.beck.model.Product;
import com.hqc.beck.repository.IAuthorsRepository;
import com.hqc.beck.repository.ICategoriesRepository;
import com.hqc.beck.repository.IEditorsRepository;
import com.hqc.beck.repository.IProductRepository;
import com.hqc.beck.request.ProductRequest;
import com.hqc.beck.services.interfaces.IProductService;
import static com.hqc.beck.utils.Utilities.buildProductDTO;
import static com.hqc.beck.utils.Utilities.buildProductDTOid;

@Service
public class ProductImplementation implements IProductService {
    private final IProductRepository productRepository;
    private final IEditorsRepository editorsRepository;
    private final IAuthorsRepository authorsRepository;
    private final ICategoriesRepository categoriesRepository;

    public ProductImplementation(IProductRepository productRepository, IEditorsRepository editorsRepository,
            IAuthorsRepository authorsRepository, ICategoriesRepository categoriesRepository) {
        this.productRepository = productRepository;
        this.editorsRepository = editorsRepository;
        this.authorsRepository = authorsRepository;
        this.categoriesRepository = categoriesRepository;
    }

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

        List<Authors> authorsList = authorsRepository.findAllById(req.getAuthorsId());
        if (authorsList.isEmpty())
            throw new Exception("❌ Nessun autore trovato per gli ID: " + req.getAuthorsId());

        List<Categories> categoryList = categoriesRepository.findAllById(req.getAuthorsId());
        if (categoryList.isEmpty())
            throw new Exception("❌ Nessuna categoria trovata per gli ID: " + req.getAuthorsId());

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
        g.setListAuthors(authorsList);
        g.setListCategory(categoryList);

        productRepository.save(g);
    }// create

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProductRequest req) throws Exception {
        Product product = productRepository.findById(req.getId())
                .orElseThrow(() -> new Exception("Product-Not-Found"));

        Editors editor = editorsRepository.findById(req.getEditorsId())
                .orElseThrow(() -> new Exception("Editor-Not-Found"));

        List<Authors> authorsList = authorsRepository.findAllById(req.getAuthorsId());
        if (authorsList.isEmpty())
            throw new Exception("❌ Nessun autore trovato per gli ID: " + req.getAuthorsId());

        List<Categories> categoryList = categoriesRepository.findAllById(req.getAuthorsId());
        if (categoryList.isEmpty())
            throw new Exception("❌ Nessuna categoria trovata per gli ID: " + req.getAuthorsId());

        product.setName(req.getName());
        product.setDescription(req.getDescription());
        product.setPrice(req.getPrice());
        product.setDate(req.getDate());
        product.setMinGameTime(req.getMinGameTime());
        product.setMaxGameTime(req.getMaxGameTime());
        product.setMinPlayerNumber(req.getMinPlayerNumber());
        product.setMaxPlayerNumber(req.getMaxPlayerNumber());
        product.setMinAge(req.getMinAge());
        product.setStockQuantity(req.getStockQuantity());
        product.setActive(req.getActive());

        product.setEditor(editor);
        product.setListAuthors(authorsList);
        product.setListCategory(categoryList);

        productRepository.save(product);
    }// update

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent())
            throw new Exception("Product-Not-Found");

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
