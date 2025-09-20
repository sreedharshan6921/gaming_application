package com.NIED.controller;

import com.NIED.model.Recharge;
import com.NIED.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recharges")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @PostMapping
    public ResponseEntity<Recharge> performRecharge(@RequestParam String memberId, @RequestParam double amount) {
        Recharge newRecharge = rechargeService.performRecharge(memberId, amount);
        if (newRecharge != null) {
            return new ResponseEntity<>(newRecharge, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}