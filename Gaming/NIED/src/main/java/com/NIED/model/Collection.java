package com.NIED.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "collections")
public class Collection {
    @Id
    private String id;
    private Date date;
    private double amount;
}