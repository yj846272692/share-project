package com.yj.content.openfeign;

import com.yj.content.common.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
