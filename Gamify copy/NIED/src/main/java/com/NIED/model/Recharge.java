package com.NIED.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "recharges")
public class Recharge {
    @Id
    private String id;
    private String memberId;
    private double amount;
    private Date dateTime = new Date();
}