package com.hqc.beck.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hqc.beck.model.Image;
import com.hqc.beck.model.Product;
import com.hqc.beck.repository.IImageRepository;
import com.hqc.beck.repository.IProductRepository;

@RequestMapping("/api")
@RestController
public class ImageController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IImageRepository imageRepository;

    // @PostMapping("/public/images")
    // public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile
    // file) {
    // try {
    // // Save the file to the directory
    // String filePath = saveImage(file);
    // return ResponseEntity.ok("Image uploaded successfully: " + filePath);
    // } catch (IOException e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error
    // uploading image");
    // }
    // }

    @PostMapping("/public/images/{productId}")
    public ResponseEntity<String> uploadImage(@PathVariable Integer productId,
            @RequestParam("file") MultipartFile file) {
        try {
            // Verifica che il prodotto esista
            Product product = productRepository.findById(productId).orElse(null);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Product not found with ID: " + productId);
            }

            // Salva l'immagine fisicamente e ottieni il percorso
            String filePath = saveImage(file);

            // Creazione dell'oggetto Image
            Image image = new Image();
            image.setUrl(filePath);
            image.setProduct(product);

            imageRepository.save(image);

            // Aggiungere l'immagine alla lista di immagini del prodotto
            if (product.getListImage() != null) {
                product.getListImage().add(image);
            } else {
                List<Image> images = new ArrayList<>();
                images.add(image);
                product.setListImage(images);
            }

            // Salvare il prodotto con la nuova immagine
            productRepository.save(product);

            return ResponseEntity.ok("Image uploaded and linked to product successfully: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading image");
        }
    }

    private String saveImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }

    @GetMapping("/public/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}// class
