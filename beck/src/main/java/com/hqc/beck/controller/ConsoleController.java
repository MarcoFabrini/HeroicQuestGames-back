package com.hqc.beck.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqc.beck.request.ConsoleRequest;
import com.hqc.beck.services.interfaces.IConsoleService;

@RequestMapping("/api")
@RestController
public class ConsoleController {
    private final IConsoleService consoleService;

    public ConsoleController(IConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @PostMapping("/admin/console/create")
    public ResponseEntity<?> create(@RequestBody ConsoleRequest req) {
        try {
            consoleService.create(req);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Collections.singletonMap("message", "Console successfully CREATED!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error"));
        }
    }// create

    @PutMapping("/admin/console/update")
    public ResponseEntity<?> update(@RequestBody ConsoleRequest req) {
        try {
            consoleService.update(req);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("message", "Console successfully UPDATED!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error: " + e.getMessage()));
        }
    }// update

}// class
