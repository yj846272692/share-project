package com.yj.content.service;

//import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yj.content.domain.dto.ShareAuditDto;
import com.yj.content.domain.entity.Share;

import java.util.List;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-06
 **/

public interface ShareService {
    /**
     * @param id id
     * @return share
     */
    //Share findById(Integer id);

    List<Share> findAll();

    Share findById(Integer id);

    Share auditShare(ShareAuditDto shareAuditDto) throws IllegalAccessException;
}
