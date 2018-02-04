package com.service;

import com.dao.MessageDao;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteBatch(String[] ids) {
        MessageDao messageDao = new MessageDao();
        List<Integer> idlist = new ArrayList<Integer>();
        for (String id : ids) {
            idlist.add(Integer.valueOf(id));
        }
        messageDao.deleteBatch(idlist);
    }
}
