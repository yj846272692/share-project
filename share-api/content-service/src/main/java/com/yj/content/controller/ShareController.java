package com.yj.content.controller;

//import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.alibaba.csp.sentinel.slots.block.BlockException;

import com.alibaba.fastjson.JSONObject;
import com.yj.content.auth.CheckAuthorization;
import com.yj.content.domain.dto.ShareAuditDto;
import com.yj.content.domain.dto.ShareDto;
import com.yj.content.domain.entity.Share;
import com.yj.content.domain.entity.User;
import com.yj.content.openfeign.UserService;
import com.yj.content.utils.JwtOperator;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import com.yj.content.common.ResponseResult;
import com.yj.content.service.ShareService;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-06
 **/

@RestController
@Slf4j
@RefreshScope
@RequestMapping(value = "/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {

    private final ShareService shareService;

    private final UserService userService;

    private final JwtOperator jwtOperator;

    @GetMapping("/all")
//    @CheckAuthorization("user")
    public ResponseResult getAllShares(@RequestParam(defaultValue = "0") Integer page,
                                       @RequestParam(defaultValue = "5") Integer size,
                                       @RequestParam(required = false) String title,
                                       @RequestParam(required = false) String name,
                                       @RequestParam(required = false) String content
    ) {
        String id = null;
        HttpServletRequest request = getHttpServletRequest();
        String token = request.getHeader("X-Token");
        if (token != null) {
            Claims claims = jwtOperator.getClaimsFromToken(token);
            id = claims.get("id").toString();
        }
        return ResponseResult.success(shareService.findAll(page, size, title, name, content, id));
    }

    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        assert attributes != null;
        return attributes.getRequest();
    }

    @GetMapping("{id}")
    ResponseResult getShareById(@PathVariable Integer id) {
        Share share = shareService.findById(id);
        Integer userId = share.getUserId();
        ResponseResult res = userService.getUser(userId);
        String jsonString = JSONObject.toJSONString(res.getData());
        JSONObject obj = JSONObject.parseObject(jsonString);
        User user = JSONObject.toJavaObject(obj, User.class);
        ShareDto shareDTO = ShareDto.builder().share(share).nickname(user.getNickname()).avatar(user.getAvatar()).build();
        return ResponseResult.success(shareDTO);
    }

    @PostMapping("/check")
    @CheckAuthorization("admin")
    ResponseResult auditContent(@RequestBody ShareAuditDto shareAuditDto) throws IllegalAccessException {
        return ResponseResult.success(shareService.auditShare(shareAuditDto));
    }
}
