package com.hqc.back.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqc.back.request.CollectibleCardRequest;
import com.hqc.back.services.interfaces.ICollectibleCardService;

@RequestMapping("/api")
@RestController
public class CollectibleCardController {

    private final ICollectibleCardService collectibleCardService;

    public CollectibleCardController(ICollectibleCardService collectibleCardService) {
        this.collectibleCardService = collectibleCardService;
    }

    @PostMapping("/admin/collectiblecard/create")
    public ResponseEntity<?> create(@RequestBody CollectibleCardRequest req) {
        try {
            collectibleCardService.create(req);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Collections.singletonMap("message", "Collectible Card successfully CREATED!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error"));
        }
    }// create

    @PutMapping("/admin/collectiblecard/update")
    public ResponseEntity<?> update(@RequestBody CollectibleCardRequest req) {
        try {
            collectibleCardService.update(req);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("message", "Collectible Card successfully UPDATED!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error: " + e.getMessage()));
        }
    }// update

}// class
