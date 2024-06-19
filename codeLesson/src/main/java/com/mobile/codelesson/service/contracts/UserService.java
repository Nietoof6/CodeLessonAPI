package com.mobile.codelesson.service.contracts;

import com.mobile.codelesson.domain.entities.Token;
import com.mobile.codelesson.domain.entities.User;

public interface UserService {
    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;

    User findUserAuthenticated();
    User findByemail(String email);
}
