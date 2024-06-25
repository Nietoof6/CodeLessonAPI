package com.mobile.codelesson.domain.dtos.req;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Integer exp;
}
