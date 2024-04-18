package com.yao.apiclientsdk.model;

import lombok.Data;

@Data
public class PasswordMatch {
    private String password;
    private String encodedPassword;
}
