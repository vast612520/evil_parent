package com.bewind.evil.service.impl;

import com.bewind.evil.dao.MessageDao;
import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.pojo.Message;
import com.bewind.evil.service.MessageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: konghuigang
 * Date:2021/4/5 12:56
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    /**
     * 查询所有的留言
     *
     * @return
     */
    @Override
    public List<Message> findAll() {
        List<Message> list = messageDao.findAll();
        return list;
    }

    /**
     * 新增留言
     *
     * @param message
     */
    @Override
    public void add(Message message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String createdTime = sdf.format(now);
        message.setCreatedTime(createdTime);
        messageDao.add(message);
    }

    /**
     * 分页查询留言
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Message> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Message> pageResult = messageDao.findPage(queryPageBean.getQueryString());
        PageResult<Message> messagePageResult = new PageResult<>(pageResult.getTotal(), pageResult.getResult());
        return messagePageResult;
    }

    /**
     * 根据id删除留言
     *
     * @param message
     */
    @Override
    public void update(Message message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String updateTime = sdf.format(now);
        message.setUpdateBy("admin");
        message.setUpdateTime(updateTime);
        messageDao.update(message);
    }

    /**
     * 根据id查询留言
     *
     * @param id
     * @return
     */
    @Override
    public Message findById(int id) {
        Message message = messageDao.findById(id);
        return message;
    }
}
