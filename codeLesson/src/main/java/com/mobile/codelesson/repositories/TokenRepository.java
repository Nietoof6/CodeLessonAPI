package com.mobile.codelesson.repositories;

import com.mobile.codelesson.domain.entities.Token;
import com.mobile.codelesson.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TokenRepository extends MongoRepository<Token, String>{
    List<Token> findByUserAndActive(User user, Boolean active);
}
