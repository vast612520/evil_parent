package com.bewind.evil.controller;



import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.entity.Result;
import com.bewind.evil.pojo.SysNotice;
import com.bewind.evil.service.ISysNoticeService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;


/**
 * 通知公告Controller
 * 
 * @author 港
 * @date 2021-03-26
 */
@RestController
@RequestMapping("/notice")
public class SysNoticeController
{
    @Autowired
    private ISysNoticeService sysNoticeService;

    /**
     * 查询通知公告列表
     */
    @PreAuthorize("hasAnyAuthority('NOTICE_QUERY')")
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean)
    {
        PageResult<SysNotice> list = sysNoticeService.selectSysNoticeList(queryPageBean);
        return new Result(true,"查询通知公告成功",list);
    }
    
    /**
     * 获取通知公告详细信息
     */
    @PreAuthorize("hasAnyAuthority('NOTICE_QUERY')")
    @GetMapping(value = "/findById")
    public Result getInfo(int id)
    {
        SysNotice sysNotice = sysNoticeService.selectSysNoticeById(id);
        return new Result(true,"操作成功",sysNotice);
    }

    /**
     * 新增通知公告
     */
    @PreAuthorize("hasAnyAuthority('NOTICE_ADD')")
    @PostMapping(value = "/add")
    public Result add(@RequestBody SysNotice SysNotice)
    {
        int cnt = sysNoticeService.insertSysNotice(SysNotice);
        return new Result(cnt>0?true:false,"操作成功",cnt);
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("hasAnyAuthority('NOTICE_EDIT')")
    @PostMapping(value = "/update")
    public Result edit(@RequestBody SysNotice SysNotice)
    {
        int cnt = sysNoticeService.updateSysNotice(SysNotice);

        return new Result(cnt>0?true:false,"操作成功");
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("hasAnyAuthority('NOTICE_DELETE')")
	@DeleteMapping("/deleteById")
    public Result remove(Integer id)
    {
        int cnt = sysNoticeService.deleteSysNoticeById(id);
        return new Result(cnt > 0 ? true:false,"操作成功");
    }
}
