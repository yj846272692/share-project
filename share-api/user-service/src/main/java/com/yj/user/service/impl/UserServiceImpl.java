package com.yj.user.service.impl;

import com.yj.user.repository.UserRepository;
import com.yj.user.service.UserService;
import com.yj.user.utils.JwtOperator;
import com.yj.user.domain.dto.UserDto;
import com.yj.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-24
 **/

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Map login(UserDto userDto) {
        JwtOperator jwtOperator = new JwtOperator();
        HashMap<String, Object> claims = new HashMap<>();
        User user = userRepository.findByMobileAndPassword(userDto.getUsername(), userDto.getPassword());
        claims.put("id", user.getId());
        claims.put("role", user.getRoles());
        claims.put("nickname",user.getNickname());
        String token = jwtOperator.generateToken(claims);
        HashMap<Object, Object> result = new HashMap<>();
//        result.put("user", user);
        result.put("token", token);
        return result;
    }
}

