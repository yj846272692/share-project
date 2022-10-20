package com.yj.user.domain.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author YangJING
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BonusEventLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer value;
    private String event;
    private Date createTime;
    private String description;
}
