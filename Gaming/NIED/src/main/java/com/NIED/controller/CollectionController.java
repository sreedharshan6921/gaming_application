package com.NIED.controller;

import com.NIED.model.Collection;
import com.NIED.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/{date}")
    public ResponseEntity<Collection> getCollectionByDate(@PathVariable String date) {
        try {
            Date collectionDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Optional<Collection> collectionOptional = collectionService.getCollectionByDate(collectionDate);
            return collectionOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}