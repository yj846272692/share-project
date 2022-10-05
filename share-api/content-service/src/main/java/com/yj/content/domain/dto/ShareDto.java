package com.yj.content.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.yj.content.domain.entity.Share;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareDto {
    private Share share;
    private String nickname;
    private String avatar;
}
