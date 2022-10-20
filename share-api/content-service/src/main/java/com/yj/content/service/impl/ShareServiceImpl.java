package com.yj.content.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.yj.content.domain.dto.ShareAuditDto;
import com.yj.content.domain.dto.UserAddBonusDto;
import com.yj.content.domain.entity.MidUserShare;
import com.yj.content.domain.enumeration.AuditStatusEnum;
import com.yj.content.service.MidUserShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.yj.content.domain.entity.Share;
import com.yj.content.repository.ShareRepository;
import com.yj.content.service.ShareService;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
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

    private final MidUserShareService midUserShareService;

    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public Share findById(Integer id) {
        return shareRepository.findById(id).orElse(null);
    }


    @Override
    public List<Share> findAll(Integer page, Integer size, String title, String name, String content, String userId) {
        if (size > 10) {
            size = 10;
        }
        Specification<Share> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(10);
            if (StringUtils.isNotBlank(title)) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            }
            if (StringUtils.isNotBlank(name)) {
                predicates.add(criteriaBuilder.like(root.get("author"), "%" + name + "%"));
            }
            if (StringUtils.isNotBlank(content)) {
                predicates.add(criteriaBuilder.like(root.get("summary"), "%" + content + "%"));
            }
            predicates.add(criteriaBuilder.equal(root.get("auditStatus"), AuditStatusEnum.PASS.name()));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        List<Share> shares = shareRepository.findAll(specification, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,
                "createTime"))).getContent();
        log.info("分享内容 数据长度为：{}", shares.size());
        if (userId != null) {
            return shares;
        } else {
            return shares.stream().map(e -> {
                Share share = new Share();
                BeanUtils.copyProperties(e, share);
                share.setDownloadUrl("");
                return share;
            }).toList();
        }


//        return shareRepository.findByTitleLikeAndAuditStatus(title, AuditStatusEnum.PASS.name(),
//                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createTime")));
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
        Share newShare = shareRepository.saveAndFlush(share);

        midUserShareService.insert(
                MidUserShare.builder()
                        .shareId(newShare.getId())
                        .userId(newShare.getUserId()).build()
        );

        if (AuditStatusEnum.PASS.equals(shareAuditDto.getAuditStatusEnum())) {
            rocketMQTemplate.convertAndSend(
                    "add-bonus",
                    UserAddBonusDto.builder().userId(newShare.getUserId())
                            .bonus(50).build()
            );
        }
        return newShare;
    }
}
