package com.NIED.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
@Data
@Document(collection = "games")
public class Game {
    @Id
    @NotBlank(message = "Name is required")
    private String id;
    private String name;
     @Positive(message = "Price must be a positive number")
    private double price;
    private String description;
}