package com.yao.yuapicommon.model.entity;

import lombok.Data;

@Data
public class PasswordMatch {
    private String password;
    private String encodedPassword;
}
