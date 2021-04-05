package com.bewind.evil.service;

import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.pojo.Message;

import java.util.List;

/**
 * Description:
 * Author: konghuigang
 * Date:2021/4/5 12:54
 */
public interface MessageService {

    /**
     * 查询所有的留言
     * @return
     */
    List<Message> findAll();

    /**
     * 新增留言
     * @param message
     */
    void add(Message message);

    /**
     * 分页查询留言
     * @param queryPageBean
     * @return
     */
    PageResult<Message> findPage(QueryPageBean queryPageBean);

    /**
     * 根据id删除留言
     * @param message
     */
    void update(Message message);

    /**
     * 根据id查询留言
     * @param id
     * @return
     */
    Message findById(int id);
}
