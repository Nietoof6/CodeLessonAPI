package com.mobile.codelesson.controller;

import com.mobile.codelesson.domain.dtos.req.UserExpResDTO;
import com.mobile.codelesson.domain.dtos.req.UserPasswordDTO;
import com.mobile.codelesson.domain.dtos.req.UserProfileDTO;
import com.mobile.codelesson.domain.dtos.res.GeneralResponse;
import com.mobile.codelesson.domain.dtos.res.TokenDTO;
import com.mobile.codelesson.domain.dtos.res.UserExpDTO;
import com.mobile.codelesson.domain.dtos.res.UserShowProfileDTO;
import com.mobile.codelesson.domain.entities.Token;
import com.mobile.codelesson.domain.entities.User;
import com.mobile.codelesson.service.contracts.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/profile")
    public ResponseEntity<GeneralResponse> getProfile() {
        try {
            User user = userService.findUserAuthenticated();
            if (user == null) {
                return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "User does not exist!");
            }
            UserShowProfileDTO userShowProfileDTO = modelMapper.map(user, UserShowProfileDTO.class);
            return GeneralResponse.getResponse(HttpStatus.OK, userShowProfileDTO);
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }

    @PostMapping("/update-password")
    public ResponseEntity<GeneralResponse> updatePassword(@RequestBody UserPasswordDTO user) {

        try {
            User user1 = userService.findUserAuthenticated();
            if (!userService.isPasswordOk(user1, user.getPassword())) {
                throw new IllegalArgumentException("Invalid password!");
            }
            User newUser = userService.updatePassword(user1, user.getNewPassword());
            Token token = userService.registerToken(newUser);
            return GeneralResponse.getResponse(HttpStatus.OK, new TokenDTO(token));
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!" + e.getMessage());
        }
    }

    @PostMapping("update-profile")
    public ResponseEntity<GeneralResponse> updateProfile(@RequestBody UserProfileDTO user) {
        try {
            User user1 = userService.findUserAuthenticated();
            if (user1 == null) {
                return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "User does not exist!");
            }
            User newUser = userService.updateProfile(user1, user);
            Token token = userService.registerToken(newUser);
            return GeneralResponse.getResponse(HttpStatus.OK, new TokenDTO(token));
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> getAllUsers() {
        try {
            return GeneralResponse.getResponse(HttpStatus.OK, userService.findAll());
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }

    @GetMapping("/exp")
    public ResponseEntity<GeneralResponse> getExp() {
        try {
            User user = userService.findUserAuthenticated();
            if (user == null) {
                return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "User does not exist!");
            }
            UserExpDTO userExpDTO = userService.getExp(user.getId());
            return GeneralResponse.getResponse(HttpStatus.OK, userExpDTO);
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }

    @PostMapping("/expUpdate")
    public ResponseEntity<GeneralResponse> updateExp(@RequestBody UserExpResDTO userExpResDTO) {
        try {
            User user = userService.findUserAuthenticated();
            if (user == null) {
                return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "User does not exist!");
            }
            User newUser = userService.updateExp(user, userExpResDTO.getExp());
            Token token = userService.registerToken(newUser);
            return GeneralResponse.getResponse(HttpStatus.OK, new TokenDTO(token));
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }
}
