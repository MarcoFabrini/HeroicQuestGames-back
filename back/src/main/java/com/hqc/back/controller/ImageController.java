package com.hqc.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqc.back.request.ImageRequest;
import com.hqc.back.services.interfaces.IImageService;

@RequestMapping("/api")
@RestController
public class ImageController {
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

    @DeleteMapping("/public/images/product/delete")
    public ResponseEntity<?> deleteImage(@RequestBody ImageRequest req) {
        try {
            imageService.deleteImage(req);
            return ResponseEntity.ok("Image deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }// getImagesByProductId
}// class
