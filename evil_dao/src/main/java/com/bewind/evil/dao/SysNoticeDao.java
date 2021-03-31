package com.bewind.evil.dao;


import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.pojo.Malfunction;
import com.bewind.evil.pojo.SysNotice;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通知公告Dao接口
 * 
 * @author 港
 * @date 2021-03-26
 */
public interface SysNoticeDao
{
    /**
     * 查询通知公告
     * 
     * @param id 通知公告ID
     * @return 通知公告
     */
    public SysNotice selectSysNoticeById(Integer id);

    /**
     * 查询通知公告列表
     * 
     * @param title
     * 通知公告
     * @return 通知公告集合
     */
    public Page<SysNotice> selectSysNoticeList(@Param(value = "title") String title);

    /**
     * 新增通知公告
     * 
     * @param SysNotice 通知公告
     * @return 结果
     */
    public int insertSysNotice(SysNotice SysNotice);

    /**
     * 修改通知公告
     * 
     * @param SysNotice 通知公告
     * @return 结果
     */
    public int updateSysNotice(SysNotice SysNotice);

    /**
     * 删除通知公告
     * 
     * @param id 通知公告ID
     * @return 结果
     */
    public int deleteSysNoticeById(Integer id);

    /**
     * 批量删除通知公告
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysNoticeByIds(Integer[] ids);
}
