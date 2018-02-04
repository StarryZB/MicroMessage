package com.service;

import com.dao.MessageDao;

/**
 * Created by StarryZB on 2018/2/4.
 */
public class MaintainService {
    public void deleteOne(String id) {
        if (id != null && !"".equals(id.trim())) {
            MessageDao messageDao = new MessageDao();
            messageDao.deleteOne(Integer.valueOf(id));
        }
    }
}
