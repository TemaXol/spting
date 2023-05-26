package com.example.demo.controller;

import com.example.demo.domain.LoginInfo;
import com.example.demo.domain.UserInfo;
import com.example.demo.exception.InvalidUserNameException;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BankController {

    Map<String, UserInfo> users = Map.of(
            "Artem", UserInfo.builder().userName("Artem").build(),
            "Dima", UserInfo.builder().userName("Dima").build(),
            "Anny", UserInfo.builder().userName("Anny").build()
    );

    @PostMapping("user/login")
    @ApiOperation("авторизация")
    public UserInfo doLogin(@RequestBody LoginInfo loginInfo) {
            if (loginInfo.getUserName().equals("Artem")) {
                return UserInfo.builder()
                        .loginDate(new Date())
                        .userName(loginInfo.getUserName())
                        .build();
            } else {
                throw new InvalidUserNameException();
            }
    }

    @GetMapping("user/getAll")
    @ApiOperation("Получение всех юзеров")
    public List<UserInfo> getAllUsersInfo() {
        return users.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
