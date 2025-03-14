package com.hqc.beck.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqc.beck.dto.ProductDTO;
import com.hqc.beck.model.Product;
import com.hqc.beck.repository.IProductRepository;
import com.hqc.beck.services.interfaces.IProductService;
import static com.hqc.beck.utils.Utilities.buildProductDTO;
import static com.hqc.beck.utils.Utilities.buildProductDTOid;

@Service
public class ProductImplementation implements IProductService {
    private final IProductRepository productRepository;

    public ProductImplementation(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> list() throws Exception {
        List<Product> listProduct = productRepository.findAll();
        return buildProductDTO(listProduct);
    }// list

    @Override
    public ProductDTO getProductById(Integer id) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found with ID: " + id));

        return buildProductDTOid(product);
    }// geProductById

    @Override
    public List<ProductDTO> searchByName(String name) throws Exception {
        List<Product> listProduct = productRepository.searchByName(name);
        return buildProductDTO(listProduct);
    }// searchByName

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent())
            throw new Exception("Product-Not-Found");

        product.get().setActive(false);

        productRepository.save(product.get());
    }// delete

}// class
