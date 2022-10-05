package com.yj.content.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     * 代审核
     */
    NOT_YET,
    /**
     * 通过
     */
    PASS,
    /**
     * 审核不通过
     */
    REJECT
}
