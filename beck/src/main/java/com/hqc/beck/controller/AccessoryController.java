package com.hqc.beck.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqc.beck.request.AccessoryRequest;
import com.hqc.beck.services.interfaces.IAccessoryService;

@RequestMapping("/api")
@RestController
public class AccessoryController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final IAccessoryService accessoryService;

    public AccessoryController(IAccessoryService accessoryService) {
        this.accessoryService = accessoryService;
    }

    @PostMapping("/admin/accessory/create")
    public ResponseEntity<?> create(@RequestBody AccessoryRequest req) {
        try {
            accessoryService.create(req);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Collections.singletonMap("message", "Accessory successfully CREATED!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error"));
        }
    }// create

    @PutMapping("/admin/accessory/update")
    public ResponseEntity<?> update(@RequestBody AccessoryRequest req) {
        try {
            accessoryService.update(req);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("message", "Accessory successfully UPDATED!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error: " + e.getMessage()));
        }
    }// update

}// class
