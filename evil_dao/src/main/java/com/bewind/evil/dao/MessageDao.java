package com.bewind.evil.dao;

import com.bewind.evil.pojo.Message;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageDao {
    /**
     * 查询所有留言数据
     * @return
     */
    List<Message> findAll();

    /**
     * 新增留言数据
     * @param
     */
    void add(Message message);

    /**
     * 分页查询数据
     * @param
     * @return
     */
    Page<Message> findPage(@Param(value = "isShow") String isShow);


    /**
     * 根据id删除留言数据
     * @param id
     */
    void deleteById(int id);

    /**
     * 修改维修详情
     * @param message
     */
    void update(Message message);

    /**
     * 根据id查询留言信息-编辑回显数据
     * @param id
     * @return
     */
    Message findById(int id);
}
