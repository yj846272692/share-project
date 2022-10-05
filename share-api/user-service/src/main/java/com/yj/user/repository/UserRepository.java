package com.yj.user.repository;

import com.yj.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-24
 **/
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * 根据用户名密码查询
     * @param mobile mobile
     * @param password password
     * @return user
     */
    User findByMobileAndPassword(String mobile, String password);
}

