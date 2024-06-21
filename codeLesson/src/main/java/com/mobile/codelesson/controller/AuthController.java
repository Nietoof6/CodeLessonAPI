package com.mobile.codelesson.controller;

import com.mobile.codelesson.domain.dtos.req.UserLoginDTO;
import com.mobile.codelesson.domain.dtos.req.UserRegisterDTO;
import com.mobile.codelesson.domain.dtos.res.GeneralResponse;
import com.mobile.codelesson.domain.dtos.res.TokenDTO;
import com.mobile.codelesson.domain.entities.Token;
import com.mobile.codelesson.domain.entities.User;
import com.mobile.codelesson.service.contracts.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> register(@RequestBody UserRegisterDTO userRegisterDTO){

        try {
            User user = userService.findByemail(userRegisterDTO.getEmail());
            if(user != null){
                return GeneralResponse.getResponse(HttpStatus.CONFLICT, "User already exists!");
            }
            userService.createUser(userRegisterDTO);
            return GeneralResponse.getResponse(HttpStatus.OK, "User created successfully!");
        }catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            User user = userService.findByemail(userLoginDTO.getEmail());
            if (user == null) {
                return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, "User not found!");
            }
            if (!userService.isPasswordOk(user, userLoginDTO.getPassword())) {
                return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, "User not found!");
            }
            Token token = userService.registerToken(user);
            return GeneralResponse.getResponse(HttpStatus.OK, new TokenDTO(token));

        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }
}
