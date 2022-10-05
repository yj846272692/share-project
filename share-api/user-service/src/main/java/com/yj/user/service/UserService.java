package com.yj.user.service;

import com.yj.user.domain.dto.UserDto;
import com.yj.user.domain.entity.User;

import java.util.Map;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-24
 **/

public interface UserService {
    /**
     * 根据id找user
     * @param id id
     * @return user
     */
    User findById(Integer id);

    User findMine(Integer id);

    /**
     * 登录
     * @param userDto userDto
     * @return user
     */
    Map login(UserDto userDto);
}
