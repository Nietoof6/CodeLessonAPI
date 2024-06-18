package com.mobile.codelesson.domain.entities;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String password;
}
