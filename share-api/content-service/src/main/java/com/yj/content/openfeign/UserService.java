package com.yj.content.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.yj.content.common.ResponseResult;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-25
 **/

@FeignClient(value = "user-service", path = "/users")
public interface UserService {
    /**
     * 调用用户服务
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    ResponseResult getUser(@PathVariable(value = "id") int id);
}
