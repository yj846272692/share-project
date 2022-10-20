package com.yj.content.repository;

import com.yj.content.domain.entity.Share;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @description:
 * @author: yj
 * @create: 2022-09-06
 **/

public interface ShareRepository extends JpaRepository<Share, Integer> {
    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return
     */
    Page<Share> findByTitleLikeAndAuditStatus(String title, String auditStatus, Pageable pageable);

    /**
     * 条件判断
     *
     * @param specification
     * @param pageable
     * @return
     */
    Page<Share> findAll(Specification<Share> specification, Pageable pageable);
}
