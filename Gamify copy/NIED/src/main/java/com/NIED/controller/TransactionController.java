package com.NIED.controller;

import com.NIED.model.Transaction;
import com.NIED.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> recordGamePlay(@RequestParam String memberId, @RequestParam String gameId) {
        Transaction newTransaction = transactionService.recordGamePlay(memberId, gameId);
        if (newTransaction != null) {
            return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}