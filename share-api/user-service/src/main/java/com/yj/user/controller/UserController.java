package com.yj.user.controller;

import com.yj.user.common.ResponseResult;
import com.yj.user.common.ResultCode;
import com.yj.user.domain.dto.UserDto;
import com.yj.user.service.UserService;
import com.yj.user.auth.CheckLogin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-24
 **/

@RestController
@Slf4j
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @GetMapping("{id}")
    @CheckLogin
    public ResponseResult getUserById(@PathVariable Integer id) {
        return ResponseResult.success(userService.findById(id));
    }

    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody UserDto userDto) {
        Map user = userService.login(userDto);
        if(user == null) {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        } else {
            return ResponseResult.success(user);
        }
    }
}
