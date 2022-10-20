package com.yj.content.service.impl;

import com.yj.content.domain.entity.MidUserShare;
import com.yj.content.repository.MidUserShareRepository;
import com.yj.content.service.MidUserShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MidUserShareServiceImpl implements MidUserShareService {
    private final MidUserShareRepository midUserShareRepository;

    @Override
    public void insert(MidUserShare midUserShare) {
        midUserShareRepository.save(midUserShare);
    }
}
