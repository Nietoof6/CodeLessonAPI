package com.mobile.codelesson.controller;

import com.mobile.codelesson.domain.dtos.req.UserPasswordDTO;
import com.mobile.codelesson.domain.dtos.req.UserProfileDTO;
import com.mobile.codelesson.domain.dtos.res.GeneralResponse;
import com.mobile.codelesson.domain.entities.User;
import com.mobile.codelesson.service.contracts.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/update-password")
    public ResponseEntity<GeneralResponse> updatePassword(@RequestBody UserPasswordDTO user) {

        try {
            User user1 = userService.findById(user.getId());
            if (!userService.isPasswordOk(user1, user.getPassword())) {
                throw new IllegalArgumentException("Invalid password!");
            }
            userService.updatePassword(user1, user.getNewPassword());
            return GeneralResponse.getResponse(HttpStatus.OK, "Password updated successfully!");
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }

    @PostMapping("update-profile")
    public ResponseEntity<GeneralResponse> updateProfile(@RequestBody UserProfileDTO user) {
        try {
            User user1 = userService.findById(user.getId());
            if (user1 == null) {
                return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "User does not exist!");
            }
            userService.updateProfile(user1, user);
            return GeneralResponse.getResponse(HttpStatus.OK, "Profile updated successfully!");
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }
}
