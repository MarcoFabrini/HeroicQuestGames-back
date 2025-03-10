package com.hqc.beck.controller;

import java.util.Collections;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqc.beck.request.AccessoryRequest;
import com.hqc.beck.services.interfaces.IAccessoryService;

@RequestMapping("/api")
@RestController
public class AccessoryController {

    private final IAccessoryService accessoryService;
    private final Logger log;

    public AccessoryController(IAccessoryService accessoryService, Logger log) {
        this.accessoryService = accessoryService;
        this.log = log;
    }

    @PostMapping("admin/accessory/create")
    public ResponseEntity<?> create(@RequestBody AccessoryRequest req) {
        try {
            accessoryService.create(req);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Collections.singletonMap("message", "Game successfully CREATED!"));
        } catch (Exception e) {
            log.error("Error during the creation of the game: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error"));
        }
    }// create

}// class
