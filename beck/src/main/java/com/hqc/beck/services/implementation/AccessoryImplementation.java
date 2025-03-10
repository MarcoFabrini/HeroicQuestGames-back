package com.hqc.beck.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqc.beck.dto.AccessoryDTO;
import com.hqc.beck.model.Accessory;
import com.hqc.beck.model.Product;
import com.hqc.beck.repository.IAccessoryRepository;
import com.hqc.beck.repository.IProductRepository;
import com.hqc.beck.request.AccessoryRequest;
import com.hqc.beck.services.interfaces.IAccessoryService;

@Service
public class AccessoryImplementation implements IAccessoryService {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IAccessoryRepository accessoryRepository;

    @Override
    public void create(AccessoryRequest req) throws Exception {
        Accessory accessory = new Accessory();
        accessory.setCompatibleWith(req.getCompatibleWith());
        accessory.setColor(req.getColor());
        accessory.setWireless(req.getWireless());
        accessory.setBatteryLife(req.getBatteryLife());
        accessory.setExtraFeatures(req.getExtraFeatures());
        accessory.setOriginalOrThirdparty(req.getOriginalOrThirdParty());

        accessoryRepository.save(accessory);

        Product product = new Product();
        product.setName(req.getName());
        product.setDate(req.getDate());
        product.setDescription(req.getDescription());
        product.setStockQuantity(req.getStockQuantity());
        product.setPrice(req.getPrice());
        product.setActive(true);

        product.setAccessory(accessory);
        // product.setEditor(editorsRepository.findById(req.getEditorsId()).orElse(null));
        // product.setListAuthors(authorsRepository.findAllById(req.getAuthorsId()));
        // product.setListCategory(categoriesRepository.findAllById(req.getCategoryId()));

        productRepository.save(product);        
    }// create

    @Override
    public AccessoryDTO listById(Integer id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listById'");
    }

}// class
