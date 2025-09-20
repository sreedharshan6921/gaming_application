package com.NIED.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
@Data
@Document(collection = "members")
public class Member {
    @Id
    @NotBlank(message = "Name is required")
    private String id;
    private String name;
    private double balance = 0.0;
    @Indexed(unique = true)
    @NotBlank(message = "Phone number is required")
    private String phone;
}