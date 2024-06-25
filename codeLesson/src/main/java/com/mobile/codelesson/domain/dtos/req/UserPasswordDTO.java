package com.mobile.codelesson.domain.dtos.req;

import lombok.Data;

@Data
public class UserPasswordDTO {
    private String password;
    private String newPassword;
}
