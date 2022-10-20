package com.yj.user.rocketmq;


import com.yj.user.domain.dto.UserAddBonusDto;
import com.yj.user.domain.entity.BonusEventLog;
import com.yj.user.domain.entity.User;
import com.yj.user.repository.BonusEventLogRepository;
import com.yj.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author YangJING
 */
@Service
@RocketMQMessageListener(consumerGroup = "consumer", topic = "add-bonus-YG")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AddBonusListener implements RocketMQListener<UserAddBonusDto> {
    private final UserRepository userRepository;
    private final BonusEventLogRepository bonusEventLogRepository;

    @Override
    public void onMessage(UserAddBonusDto userAddBonusDto) {
        log.info("消息来啦");
        Integer userId = userAddBonusDto.getUserId();
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setBonus(user.getBonus() + userAddBonusDto.getBonus());
        }

        

        bonusEventLogRepository.save(BonusEventLog.builder().userId(userId).value(userAddBonusDto.getBonus()).event("CONTRIBUTE").createTime(new Date()).description("投稿加积分").build());
    }
}
