package com.mobile.codelesson.repositories;

import com.mobile.codelesson.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
}
