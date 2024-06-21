package com.mobile.codelesson.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "user_token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "code")
    private UUID code;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp", updatable = false)
    private Date timestamp;

    @Column(name = "active")
    private Boolean active;

    @JoinColumn(name = "user_code")
    private User user;

    public Token(String content, User user) {
        super();
        this.content = content;
        this.user = user;
        this.timestamp = Date.from(Instant.now());
        this.active = true;
    }
}
