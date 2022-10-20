package com.yj.user.repository;


import com.yj.user.domain.entity.BonusEventLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author YangJING
 */
public interface BonusEventLogRepository extends JpaRepository<BonusEventLog, Integer> {
}
