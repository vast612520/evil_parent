package com.bewind.evil.service;


import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.pojo.SysNotice;



/**
 * 通知公告Service接口
 * 
 * @author 港
 * @date 2021-03-26
 */
public interface ISysNoticeService
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
     * @param SysNotice 通知公告
     * @return 通知公告集合
     */
    public PageResult<SysNotice> selectSysNoticeList(QueryPageBean SysNotice);

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
     * 批量删除通知公告
     * 
     * @param ids 需要删除的通知公告ID
     * @return 结果
     */
    public int deleteSysNoticeByIds(Integer[] ids);

    /**
     * 删除通知公告信息
     * 
     * @param id 通知公告ID
     * @return 结果
     */
    public int deleteSysNoticeById(Integer id);
}
