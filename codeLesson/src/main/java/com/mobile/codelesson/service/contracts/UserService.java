package com.mobile.codelesson.service.contracts;

import com.mobile.codelesson.domain.dtos.req.UserProfileDTO;
import com.mobile.codelesson.domain.dtos.req.UserRegisterDTO;
import com.mobile.codelesson.domain.entities.Token;
import com.mobile.codelesson.domain.entities.User;

public interface UserService {
    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;

    User findUserAuthenticated();
    User findByemail(String email);
    void createUser(UserRegisterDTO user);
    Boolean isPasswordOk(User user, String password);
    void updatePassword(User user, String password);
    User findById(String id);
    void updateProfile(User user, UserProfileDTO userProfileDTO);
}
