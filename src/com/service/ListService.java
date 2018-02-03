package com.service;

import com.bean.Message;
import com.dao.MessageDao;

import java.util.List;

/**
 * Created by StarryZB on 2018/2/3.
 * 列表相关业务功能
 */
public class ListService {
    public List<Message> queryMessageList(String command, String description) {
        MessageDao messageDao = new MessageDao();
        return messageDao.queryMessageList(command,description);
    }
}
