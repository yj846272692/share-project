package com.yy.notice.service;

import com.yy.notice.entity.Notice;

import java.util.List;

/**
 * @description:
 * @author: yy
 * @create: 2022-09-25
 **/

public interface NoticeService {
    /**
     * 获取最新通知
     *
     * @return notice
     */
    Notice getLatestNotice();

    List<Notice> getAll();
}

