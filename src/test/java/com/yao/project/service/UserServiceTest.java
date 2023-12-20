package com.yao.project.service;

import com.yao.yuapicommon.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 用户服务测试
 *
 * @author <a href="https://github.com/liyao">程序员鱼皮</a>
 * @from <a href="https://yao.icu">编程导航知识星球</a>
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        LinkedList linkedList = new LinkedList<>();
        User user = new User();
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        boolean result = userService.updateById(user);
        Assertions.assertTrue(result);
    }

    @Test
    void testDeleteUser() {
        boolean result = userService.removeById(1L);
        Assertions.assertTrue(result);
    }

    // [加入我们](https://yao.icu) 从 0 到 1 项目实战，经验拉满！10+ 原创项目手把手教程、7 日项目提升训练营、1000+ 项目经验笔记、60+ 编程经验分享直播

    @Test
    void testGetUser() {
        User user = userService.getById(1L);
        Assertions.assertNotNull(user);
    }

    @Test
    void userRegister() {
        String userAccount = "yao";
        String userPassword = "";
        String checkPassword = "123456";
        try {
            long result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            userAccount = "yu";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            userAccount = "yao";
            userPassword = "123456";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            userAccount = "yu pi";
            userPassword = "12345678";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            checkPassword = "123456789";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            userAccount = "dogyao";
            checkPassword = "12345678";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            userAccount = "yao";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
        } catch (Exception e) {

        }
    }

    @Test
    void testNumber(){
        int[][] points = {
                {-2147483646, -2147483645},
                {2147483646, 2147483647}
        };
        Arrays.sort(points,(a, b)->{
            return a[0]-b[0];
        });
        int res=1;
        int start=0;

        while(start < points.length){
            int i = start +1;
            for(; i < points.length;i++){
                if(points[start][1] < points[i][0]) {
                    start =i;
                    res++;
                    break;
                }
            }
            if(i==points.length){
                start = points.length;}
        }
        System.out.println("================================");

        System.out.println(res);
        System.out.println("================================");
    }
}