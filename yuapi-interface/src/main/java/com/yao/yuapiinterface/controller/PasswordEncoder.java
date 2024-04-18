package com.yao.yuapiinterface.controller;


import cn.hutool.core.util.RandomUtil;


import com.yao.apiclientsdk.model.PasswordEncode;
import com.yao.apiclientsdk.model.PasswordMatch;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
@RestController
@RequestMapping("password")
public class PasswordEncoder {
    @GetMapping("encode")
    public  String encode(@RequestBody PasswordEncode password) {
        // 生成盐
        String salt = RandomUtil.randomString(20);
        // 加密
        return encode(password.getPassword(),salt);
    }
    public  String encode(String password, String salt) {
        // 加密
        return salt + "@" + DigestUtils.md5DigestAsHex((password + salt).getBytes(StandardCharsets.UTF_8));
    }


    @GetMapping("matches")
    public  Boolean matches(@RequestBody PasswordMatch match) {
        String encodedPassword = match.getEncodedPassword();
        String password = match.getPassword();
        if (encodedPassword == null || password == null) {
            return false;
        }
        if(!encodedPassword.contains("@")){
            return false;
        }
        String[] arr = encodedPassword.split("@");
        // 获取盐
        String salt = arr[0];
        // 比较
        return encodedPassword.equals(encode(password, salt));
    }
}
