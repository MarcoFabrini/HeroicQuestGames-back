package com.hqc.beck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqc.beck.repository.IImageRepository;
import com.hqc.beck.repository.IProductRepository;
import com.hqc.beck.request.ImageRequest;
import com.hqc.beck.services.interfaces.IImageService;

@RequestMapping("/api")
@RestController
public class ImageController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    private IImageService imageService;

    @PostMapping("/public/images/upload")
    public ResponseEntity<String> uploadImage(@ModelAttribute ImageRequest imageRequest) {
        try {
            imageService.uploadImage(imageRequest);
            return ResponseEntity.ok("Image uploaded");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading image");
        }
    }// uploadImage

    @PostMapping("/public/images/product")
    public ResponseEntity<?> getImagesByProductId(@RequestBody ImageRequest req) {
        try {
            List<String> imageUrls = imageService.getImagesByProductId(req);
            return ResponseEntity.ok(imageUrls);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }// getImagesByProductId

    @GetMapping("/public/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Resource resource = imageService.getImage(filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Puoi adattarlo per supportare PNG, WEBP, ecc.
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }// getImage
}// class
