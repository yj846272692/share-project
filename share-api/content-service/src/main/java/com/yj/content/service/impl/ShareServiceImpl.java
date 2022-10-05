package com.yj.content.service.impl;

import com.yj.content.domain.dto.ShareAuditDto;
import com.yj.content.domain.entity.Share;
import com.yj.content.domain.enumeration.AuditStatusEnum;
import com.yj.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.yj.content.repository.ShareRepository;

import java.util.Objects;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-06
 **/

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {
    final private ShareRepository shareRepository;

    @Override
    public Share findById(Integer id) {
        return shareRepository.findById(id).orElse(null);
    }


    @Override
    public Page<Share> findAll(Integer page, Integer size) {
        return shareRepository.findByAuditStatus(AuditStatusEnum.PASS.name(),
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createTime")));
    }


    @Override
    public Share auditShare(ShareAuditDto shareAuditDto) throws IllegalAccessException {
        Share share = shareRepository.findById(shareAuditDto.getId()).orElse(null);
        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalAccessException("参数非法！改分享已审核！");
        }
        share.setAuditStatus(shareAuditDto.getAuditStatusEnum().toString());
        share.setReason(shareAuditDto.getReason());
        share.setShowFlag(shareAuditDto.getShowFlag());
        return shareRepository.saveAndFlush(share);
    }
}
