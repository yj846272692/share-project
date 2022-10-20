package com.yj.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YangJING
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserAddBonusDto {
    private Integer userId;
    private Integer bonus;
}
