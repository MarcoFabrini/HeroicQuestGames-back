package com.hqc.beck.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqc.beck.dto.ProductDTO;
import com.hqc.beck.request.ProductRequest;
import com.hqc.beck.services.interfaces.IProductService;

@RequestMapping("/api")
@RestController
public class ProductController {
    private final IProductService productService;
    private final Logger log;

    public ProductController(IProductService productService, Logger log) {
        this.productService = productService;
        this.log = log;
    }

    @GetMapping("public/product/list")
    public ResponseEntity<?> list() {
        try {
            List<ProductDTO> list = productService.list();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error"));
        }
    }// list

    @GetMapping("/public/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        try {
            ProductDTO product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }// getProductById

    @PostMapping("admin/product/create")
    public ResponseEntity<?> create(@RequestBody ProductRequest req) {
        try {
            productService.create(req);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Collections.singletonMap("message", "Game successfully CREATED!"));
        } catch (Exception e) {
            log.error("Error during the creation of the game: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error"));
        }
    }// create

    @PutMapping("admin/product/update")
    public ResponseEntity<?> update(@RequestBody ProductRequest req) {
        try {
            productService.update(req);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("message", "Product successfully UPDATED!"));
        } catch (Exception e) {
            log.error("Error during the update of the product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error"));
        }
    }// update

    @DeleteMapping("admin/product/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            productService.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("message", "Product successfully DELETED!"));
        } catch (Exception e) {
            log.error("Error during the deletion of the product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error"));
        }
    }// delete

}// class
