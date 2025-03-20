package com.hqc.back.services.implementation;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqc.back.model.CollectibleCard;
import com.hqc.back.model.Product;
import com.hqc.back.repository.IAuthorsRepository;
import com.hqc.back.repository.ICategoriesRepository;
import com.hqc.back.repository.ICollectibleCardRepository;
import com.hqc.back.repository.IEditorsRepository;
import com.hqc.back.repository.IProductRepository;
import com.hqc.back.request.CollectibleCardRequest;
import com.hqc.back.services.interfaces.ICollectibleCardService;

@Service
public class CollectibleCardImplementation implements ICollectibleCardService {

    private final IProductRepository productRepository;
    private final ICollectibleCardRepository collectibleCardRepository;
    private final IEditorsRepository editorsRepository;
    private final IAuthorsRepository authorsRepository;
    private final ICategoriesRepository categoriesRepository;
    private final Logger log;

    public CollectibleCardImplementation(IProductRepository productRepository,
            ICollectibleCardRepository collectibleCardRepository, IEditorsRepository editorsRepository,
            IAuthorsRepository authorsRepository, ICategoriesRepository categoriesRepository, Logger log) {
        this.productRepository = productRepository;
        this.collectibleCardRepository = collectibleCardRepository;
        this.editorsRepository = editorsRepository;
        this.authorsRepository = authorsRepository;
        this.categoriesRepository = categoriesRepository;
        this.log = log;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CollectibleCardRequest req) throws Exception {
        if (req == null)
            throw new Exception("Request cannot be null");

        if (req.getName() == null || req.getName().trim().isEmpty())
            throw new Exception("Product name cannot be null or empty");

        if (req.getPrice() == null || req.getPrice() <= 0)
            throw new Exception("Product price must be greater than 0");

        if (req.getStockQuantity() == null || req.getStockQuantity() < 0)
            throw new Exception("Stock quantity cannot be negative");

        CollectibleCard collectibleCard = new CollectibleCard();
        collectibleCard.setCardSet(req.getCardSet());
        collectibleCard.setRarity(req.getRarity());
        collectibleCard.setEdition(req.getEdition());
        collectibleCard.setLanguage(req.getLanguage());
        collectibleCard.setCondition(req.getCondition());
        collectibleCard.setHolographic(req.getHolographic());
        collectibleCard.setGraded(req.getGraded());
        collectibleCard.setGradeAuthority(req.getGradeAuthority());
        collectibleCard.setGradeScore(req.getGradeScore());

        collectibleCard = collectibleCardRepository.save(collectibleCard);

        Product product = new Product();
        product.setName(req.getName());
        product.setDate(req.getDate());
        product.setDescription(req.getDescription());
        product.setStockQuantity(req.getStockQuantity());
        product.setPrice(req.getPrice());
        product.setActive(true);

        product.setCollectibleCard(collectibleCard);

        if (req.getEditorsId() != null)
            product.setEditor(editorsRepository.findById(req.getEditorsId()).orElse(null));

        if (req.getAuthorsId() != null && !req.getAuthorsId().isEmpty())
            product.setListAuthors(authorsRepository.findAllById(req.getAuthorsId()));

        if (req.getCategoryId() != null && !req.getCategoryId().isEmpty())
            product.setListCategory(categoriesRepository.findAllById(req.getCategoryId()));

        productRepository.save(product);
        log.debug("Product and Collectible Card successfully created!");
    }// create

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CollectibleCardRequest req) throws Exception {
        if (req == null)
            throw new Exception("Request cannot be null");

        if (req.getCollectibleCardId() == null)
            throw new Exception("Collectible Card ID cannot be null");

        if (req.getProductId() == null)
            throw new Exception("Product ID cannot be null");

        CollectibleCard collectibleCard = collectibleCardRepository.findById(req.getCollectibleCardId())
                .orElseThrow(() -> new Exception("Collectible Card not found with ID: " + req.getCollectibleCardId()));

        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new Exception("Product not found with ID: " + req.getProductId()));

        collectibleCard.setCardSet(req.getCardSet());
        collectibleCard.setRarity(req.getRarity());
        collectibleCard.setEdition(req.getEdition());
        collectibleCard.setLanguage(req.getLanguage());
        collectibleCard.setCondition(req.getCondition());
        collectibleCard.setHolographic(req.getHolographic());
        collectibleCard.setGraded(req.getGraded());
        collectibleCard.setGradeAuthority(req.getGradeAuthority());
        collectibleCard.setGradeScore(req.getGradeScore());

        collectibleCardRepository.save(collectibleCard);

        product.setName(req.getName());
        product.setDate(req.getDate());
        product.setDescription(req.getDescription());
        product.setStockQuantity(req.getStockQuantity());
        product.setPrice(req.getPrice());
        product.setActive(req.getActive());

        if (req.getEditorsId() != null)
            product.setEditor(editorsRepository.findById(req.getEditorsId()).orElse(null));

        if (req.getAuthorsId() != null && !req.getAuthorsId().isEmpty())
            product.setListAuthors(authorsRepository.findAllById(req.getAuthorsId()));

        if (req.getCategoryId() != null && !req.getCategoryId().isEmpty())
            product.setListCategory(categoriesRepository.findAllById(req.getCategoryId()));

        product.setCollectibleCard(collectibleCard);

        productRepository.save(product);

        log.debug("Collectible Card and Product successfully updated!");
    }// update

}// class
