package com.bewind.evil.dao;

import com.github.pagehelper.Page;
import com.bewind.evil.pojo.Malfunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MalfunctionDao {
    /**
     * 查询所有维修详情数据
     * @return
     */
    List<Malfunction> findAll();

    /**
     * 新增维修详情数据
     * @param Malfunction
     */
    void add(Malfunction Malfunction);

    /**
     * 分页查询数据
     * @param
     * @return
     */
    Page<Malfunction> findPage(@Param(value = "isFinish") String isFinish);


    /**
     * 根据id删除维修详情数据
     * @param id
     */
    void deleteById(int id);

    /**
     * 修改维修详情
     * @param Malfunction
     */
    void update(Malfunction Malfunction);

    /**
     * 根据id查询用户信息-编辑回显数据
     * @param id
     * @return
     */
    Malfunction findById(int id);
}
