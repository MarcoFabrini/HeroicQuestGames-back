package com.hqc.beck.services.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqc.beck.model.Accessory;
import com.hqc.beck.model.Authors;
import com.hqc.beck.model.Categories;
import com.hqc.beck.model.Product;
import com.hqc.beck.repository.IAccessoryRepository;
import com.hqc.beck.repository.IAuthorsRepository;
import com.hqc.beck.repository.ICategoriesRepository;
import com.hqc.beck.repository.IEditorsRepository;
import com.hqc.beck.repository.IProductRepository;
import com.hqc.beck.request.AccessoryRequest;
import com.hqc.beck.services.interfaces.IAccessoryService;

@Service
public class AccessoryImplementation implements IAccessoryService {

    private final IProductRepository productRepository;
    private final IAccessoryRepository accessoryRepository;
    private final IEditorsRepository editorsRepository;
    private final IAuthorsRepository authorsRepository;
    private final ICategoriesRepository categoriesRepository;
    private final Logger log;

    public AccessoryImplementation(IProductRepository productRepository, IAccessoryRepository accessoryRepository,
            IEditorsRepository editorsRepository, IAuthorsRepository authorsRepository,
            ICategoriesRepository categoriesRepository, Logger log) {
        this.productRepository = productRepository;
        this.accessoryRepository = accessoryRepository;
        this.editorsRepository = editorsRepository;
        this.authorsRepository = authorsRepository;
        this.categoriesRepository = categoriesRepository;
        this.log = log;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(AccessoryRequest req) throws Exception {
        if (req == null)
            throw new Exception("Request cannot be null");

        if (req.getName() == null || req.getName().trim().isEmpty())
            throw new Exception("Product name cannot be null or empty");

        if (req.getPrice() == null || req.getPrice() <= 0)
            throw new Exception("Product price must be greater than 0");

        if (req.getStockQuantity() == null || req.getStockQuantity() < 0)
            throw new Exception("Stock quantity cannot be negative");

        Accessory accessory = new Accessory();
        accessory.setCompatibleWith(req.getCompatibleWith());
        accessory.setColor(req.getColor());
        accessory.setWireless(req.getWireless());
        accessory.setBatteryLife(req.getBatteryLife());
        accessory.setExtraFeatures(req.getExtraFeatures());
        accessory.setOriginalOrThirdparty(req.getOriginalOrThirdParty());

        accessory = accessoryRepository.save(accessory);

        Product product = new Product();
        product.setName(req.getName());
        product.setDate(req.getDate());
        product.setDescription(req.getDescription());
        product.setStockQuantity(req.getStockQuantity());
        product.setPrice(req.getPrice());
        product.setActive(true);

        product.setAccessory(accessory);

        // Associare Editor solo se esiste
        if (req.getEditorsId() != null) {
            editorsRepository.findById(req.getEditorsId()).ifPresent(product::setEditor);
        }

        // Associare Autori solo se esistono
        if (req.getAuthorsId() != null && !req.getAuthorsId().isEmpty()) {
            List<Authors> authors = authorsRepository.findAllById(req.getAuthorsId());
            if (!authors.isEmpty()) {
                product.setListAuthors(authors);
            }
        }

        // Associare Categorie solo se esistono
        if (req.getCategoryId() != null && !req.getCategoryId().isEmpty()) {
            List<Categories> categories = categoriesRepository.findAllById(req.getCategoryId());
            if (!categories.isEmpty()) {
                product.setListCategory(categories);
            }
        }

        productRepository.save(product);
        log.debug("Product and Accessory successfully created!");
    }// create

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AccessoryRequest req) throws Exception {
        if (req == null)
            throw new Exception("Request cannot be null");

        if (req.getAccessoryId() == null)
            throw new Exception("Accessory ID cannot be null");

        if (req.getProductId() == null)
            throw new Exception("Product ID cannot be null");

        Accessory accessory = accessoryRepository.findById(req.getAccessoryId())
                .orElseThrow(() -> new Exception("Accessory not found with ID: " + req.getAccessoryId()));

        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new Exception("Product not found with ID: " + req.getProductId()));

        accessory.setCompatibleWith(req.getCompatibleWith());
        accessory.setColor(req.getColor());
        accessory.setWireless(req.getWireless());
        accessory.setBatteryLife(req.getBatteryLife());
        accessory.setExtraFeatures(req.getExtraFeatures());
        accessory.setOriginalOrThirdparty(req.getOriginalOrThirdParty());

        accessoryRepository.save(accessory);

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

        product.setAccessory(accessory);

        productRepository.save(product);

        log.debug("Accessory and Product successfully updated!");
    }// update

}// class
