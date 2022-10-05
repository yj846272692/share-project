package com.yj.notice.service.impl;

import com.yj.notice.entity.Notice;
import com.yj.notice.service.NoticeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.yj.notice.repository.NoticeRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-25
 **/

@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeRepository noticeRepository;

    @Override
    public Notice getLatestNotice() {
        Sort sort = Sort.by("createTime").descending();
        return noticeRepository.findByShowFlag(true, sort).get(0);
    }

    @Override
    public List<Notice> getAll() {
        return noticeRepository.findAll();
    }
}
