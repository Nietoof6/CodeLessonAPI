package com.mobile.codelesson.domain.dtos.res;

import lombok.Data;

@Data
public class UserShowProfileDTO {
    private String name;
    private String lastName;
    private String email;
    private String exp;
}
