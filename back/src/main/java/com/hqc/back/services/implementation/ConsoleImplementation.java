package com.hqc.back.services.implementation;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.hqc.back.model.Console;
import com.hqc.back.model.Product;
import com.hqc.back.repository.IAuthorsRepository;
import com.hqc.back.repository.ICategoriesRepository;
import com.hqc.back.repository.IConsoleRepository;
import com.hqc.back.repository.IEditorsRepository;
import com.hqc.back.repository.IProductRepository;
import com.hqc.back.request.ConsoleRequest;
import com.hqc.back.services.interfaces.IConsoleService;

@Service
public class ConsoleImplementation implements IConsoleService {

    private final IProductRepository productRepository;
    private final IConsoleRepository consoleRepository;
    private final IEditorsRepository editorsRepository;
    private final IAuthorsRepository authorsRepository;
    private final ICategoriesRepository categoriesRepository;
    private final Logger log;

    public ConsoleImplementation(IProductRepository productRepository, IConsoleRepository consoleRepository,
            IEditorsRepository editorsRepository, IAuthorsRepository authorsRepository,
            ICategoriesRepository categoriesRepository, Logger log) {
        this.productRepository = productRepository;
        this.consoleRepository = consoleRepository;
        this.editorsRepository = editorsRepository;
        this.authorsRepository = authorsRepository;
        this.categoriesRepository = categoriesRepository;
        this.log = log;
    }

    @Override
    public void create(ConsoleRequest req) throws Exception {
        if (req == null)
            throw new Exception("Request cannot be null");

        if (req.getName() == null || req.getName().trim().isEmpty())
            throw new Exception("Product name cannot be null or empty");

        if (req.getPrice() == null || req.getPrice() <= 0)
            throw new Exception("Product price must be greater than 0");

        if (req.getStockQuantity() == null || req.getStockQuantity() < 0)
            throw new Exception("Stock quantity cannot be negative");

        Console console = new Console();
        console.setBrand(req.getBrand());
        console.setModel(req.getModel());
        console.setStorageCapacity(req.getStorageCapacity());
        console.setCondition(req.getCondition());
        console.setWarrantyMonths(req.getWarrantyMonths());
        console.setIncludedAccessories(req.getIncludedAccessories());

        console = consoleRepository.save(console);

        Product product = new Product();
        product.setName(req.getName());
        product.setDate(req.getDate());
        product.setDescription(req.getDescription());
        product.setStockQuantity(req.getStockQuantity());
        product.setPrice(req.getPrice());
        product.setActive(true);

        product.setConsole(console);

        if (req.getEditorsId() != null)
            product.setEditor(editorsRepository.findById(req.getEditorsId()).orElse(null));

        if (req.getAuthorsId() != null && !req.getAuthorsId().isEmpty())
            product.setListAuthors(authorsRepository.findAllById(req.getAuthorsId()));

        if (req.getCategoryId() != null && !req.getCategoryId().isEmpty())
            product.setListCategory(categoriesRepository.findAllById(req.getCategoryId()));

        productRepository.save(product);
        log.debug("Product and Console successfully created!");
    }// create

    @Override
    public void update(ConsoleRequest req) throws Exception {
        if (req == null)
            throw new Exception("Request cannot be null");

        if (req.getConsoleId() == null)
            throw new Exception("Console ID cannot be null");

        if (req.getProductId() == null)
            throw new Exception("Product ID cannot be null");

        Console console = consoleRepository.findById(req.getConsoleId())
                .orElseThrow(() -> new Exception("Accessory not found with ID: " + req.getConsoleId()));

        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new Exception("Product not found with ID: " + req.getProductId()));

        console.setBrand(req.getBrand());
        console.setModel(req.getModel());
        console.setStorageCapacity(req.getStorageCapacity());
        console.setCondition(req.getCondition());
        console.setWarrantyMonths(req.getWarrantyMonths());
        console.setIncludedAccessories(req.getIncludedAccessories());

        consoleRepository.save(console);

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

        product.setConsole(console);

        productRepository.save(product);

        log.debug("Console and Product successfully updated!");
    }// update

}// class
