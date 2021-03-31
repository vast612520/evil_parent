package com.bewind.evil.service.impl;


import com.bewind.evil.dao.MalfunctionDao;
import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.exception.EvilException;
import com.bewind.evil.pojo.Malfunction;
import com.bewind.evil.service.MalfunctionService;
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
 * Date:2021/3/23 18:18
 */
@Service
public class MalfunctionServiceImpl implements MalfunctionService {
    @Autowired
    private MalfunctionDao malfunctionDao;
    /**
     * 查询所有检修项
     *
     * @return
     */
    @Override
    public List<Malfunction> findAll() {
        return malfunctionDao.findAll();
    }

    /**
     * 新增检修项数据
     *
     * @param malfunction
     */
    @Override
    public void add(Malfunction malfunction) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createTime = sdf.format(new Date());
        malfunction.setCreateTime(createTime);
        malfunctionDao.add(malfunction);
    }

    /**
     * 分页查询检修项数据
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Malfunction> findPage(QueryPageBean queryPageBean) {
        // 使用PageHelper调用startPage方法,传入当前页面和页面大小
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        //调用dao进行分页查询数据
        Page<Malfunction> page = malfunctionDao.findPage(queryPageBean.getQueryString());

        //将数据封装到pageResult中
        PageResult<Malfunction> pageResult = new PageResult<>(page.getTotal(), page.getResult());

        return pageResult;
    }

    /**
     * 根据id删除检修项数据
     *
     * @param id
     */
    @Override
    public void deleteById(int id) throws EvilException {
        malfunctionDao.deleteById(id);
    }

    /**
     * 修改检修项数据
     *
     * @param malfunction
     */
    @Override
    public void update(Malfunction malfunction) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(new Date());
        malfunction.setUpdateTime(createTime);
        malfunction.setIsFinish("1");
        malfunctionDao.update(malfunction);
    }

    /**
     * 根据id查询用户信息-编辑回显数据
     *
     * @param id
     * @return
     */
    @Override
    public Malfunction findById(int id) {
        return malfunctionDao.findById(id);
    }
}
