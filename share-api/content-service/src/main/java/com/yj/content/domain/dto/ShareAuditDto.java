package com.yj.content.domain.dto;

import com.yj.content.domain.enumeration.AuditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareAuditDto {
    private Integer id;
    private AuditStatusEnum auditStatusEnum;
    private String reason;
    private Boolean showFlag;
}
