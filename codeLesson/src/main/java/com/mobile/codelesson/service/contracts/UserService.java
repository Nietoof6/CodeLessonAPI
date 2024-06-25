package com.mobile.codelesson.service.contracts;

import com.mobile.codelesson.domain.dtos.req.UserProfileDTO;
import com.mobile.codelesson.domain.dtos.req.UserRegisterDTO;
import com.mobile.codelesson.domain.dtos.res.UserExpDTO;
import com.mobile.codelesson.domain.dtos.res.UserShowProfileDTO;
import com.mobile.codelesson.domain.entities.Token;
import com.mobile.codelesson.domain.entities.User;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface UserService {
    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;

    User findUserAuthenticated();
    User findByemail(String email);
    void createUser(UserRegisterDTO user);
    Boolean isPasswordOk(User user, String password);
    User updatePassword(User user, String password);
    User findById(String id);
    UserShowProfileDTO findByIdShowProfile(String id);
    User updateProfile(User user, UserProfileDTO userProfileDTO);
    List<User> findAll();
    UserExpDTO getExp(String id);
    User updateExp(User user, int exp);
}
