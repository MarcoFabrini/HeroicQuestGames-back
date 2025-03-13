package com.hqc.beck.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqc.beck.request.BoardGameRequest;
import com.hqc.beck.services.interfaces.IBoardGameService;

@RequestMapping("/api")
@RestController
public class BoardGameController {

    private final IBoardGameService boardGameService;

    public BoardGameController(IBoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @PostMapping("/admin/boardgame/create")
    public ResponseEntity<?> create(@RequestBody BoardGameRequest req) {
        try {
            boardGameService.create(req);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Collections.singletonMap("message", "Board Game successfully CREATED!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error"));
        }
    }// create

     @PutMapping("/admin/boardgame/update")
    public ResponseEntity<?> update(@RequestBody BoardGameRequest req) {
        try {
            boardGameService.update(req);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("message", "Board Game successfully UPDATED!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal server error: " + e.getMessage()));
        }
    }// update
    
}// class
