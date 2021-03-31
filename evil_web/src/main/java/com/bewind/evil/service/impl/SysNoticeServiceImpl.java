package com.bewind.evil.service.impl;




import com.bewind.evil.dao.SysNoticeDao;
import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.pojo.SysNotice;
import com.bewind.evil.service.ISysNoticeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 通知公告Service业务层处理
 * 
 * @author 港
 * @date 2021-03-26
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService
{
    @Autowired
    private SysNoticeDao sysNoticeMapper;

    /**
     * 查询通知公告
     * 
     * @param id 通知公告ID
     * @return 通知公告
     */
    @Override
    public SysNotice selectSysNoticeById(Integer id)
    {
        return sysNoticeMapper.selectSysNoticeById(id);
    }

    /**
     * 查询通知公告列表
     * 
     * @param SysNotice 通知公告
     * @return 通知公告
     */
    @Override
    public PageResult<SysNotice> selectSysNoticeList(QueryPageBean SysNotice)
    {
        // 使用PageHelper调用startPage方法,传入当前页面和页面大小
        PageHelper.startPage(SysNotice.getCurrentPage(), SysNotice.getPageSize());

        //调用dao进行分页查询数据
        Page<SysNotice> page = sysNoticeMapper.selectSysNoticeList(SysNotice.getQueryString());

        //将数据封装到pageResult中
        PageResult<SysNotice> pageResult = new PageResult<>(page.getTotal(), page.getResult());

        return pageResult;
    }

    /**
     * 新增通知公告
     * 
     * @param SysNotice 通知公告
     * @return 结果
     */
    @Override
    public int insertSysNotice(SysNotice SysNotice)
    {
        return sysNoticeMapper.insertSysNotice(SysNotice);
    }

    /**
     * 修改通知公告
     * 
     * @param SysNotice 通知公告
     * @return 结果
     */
    @Override
    public int updateSysNotice(SysNotice SysNotice)
    {
        return sysNoticeMapper.updateSysNotice(SysNotice);
    }

    /**
     * 批量删除通知公告
     * 
     * @param ids 需要删除的通知公告ID
     * @return 结果
     */
    @Override
    public int deleteSysNoticeByIds(Integer[] ids)
    {
        return sysNoticeMapper.deleteSysNoticeByIds(ids);
    }

    /**
     * 删除通知公告信息
     * 
     * @param id 通知公告ID
     * @return 结果
     */
    @Override
    public int deleteSysNoticeById(Integer id)
    {
        return sysNoticeMapper.deleteSysNoticeById(id);
    }
}
