package com.bewind.evil.service;

import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;

import com.bewind.evil.exception.EvilException;
import com.bewind.evil.pojo.Malfunction;

import java.util.List;

public interface MalfunctionService {
    /**
     * 查询所有检修项
     * @return
     */
    List<Malfunction> findAll();

    /**
     * 新增检修项数据
     * @param malfunction
     */
    void add(Malfunction malfunction);

    /**
     * 分页查询检修项数据
     * @param queryPageBean
     * @return
     */
    PageResult<Malfunction> findPage(QueryPageBean queryPageBean);

    /**
     * 根据id删除检修项数据
     * @param id
     */
    void deleteById(int id)throws EvilException;

    /**
     * 修改检修项数据
     * @param malfunction
     */
    void update(Malfunction malfunction);

    /**
     * 根据id查询用户信息-编辑回显数据
     * @param id
     * @return
     */
    Malfunction findById(int id);
}
