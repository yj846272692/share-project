package com.yj.content.repository;

import com.yj.content.domain.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-06
 **/

public interface ShareRepository extends JpaRepository<Share, Integer> {
}
