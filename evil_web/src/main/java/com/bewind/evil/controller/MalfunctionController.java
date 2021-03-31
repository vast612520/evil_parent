package com.bewind.evil.controller;


import com.bewind.evil.constant.MessageConstant;
import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.entity.Result;
import com.bewind.evil.pojo.Malfunction;
import com.bewind.evil.service.MalfunctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/malfunction")
public class MalfunctionController {

    @Autowired
    private MalfunctionService malfunctionService;

    /**
     * 查询所有的维修数据
     * @return
     */
    @RequestMapping("/findAll")
    @PreAuthorize("hasAnyAuthority('CHECKITEM_QUERY')")
    public Result findAll(){
        //调用服务查询维修数据
        List<Malfunction> list = malfunctionService.findAll();
        return new Result(true, MessageConstant.QUERY_MALFUNCTION_SUCCESS, list);
    }

    /**
     * 新增维修数据
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")
    public Result add(@RequestBody Malfunction malfunction){
        //调用服务新增维修数据
        malfunctionService.add(malfunction);
        return new Result(true, MessageConstant.ADD_MALFUNCTION_SUCCESS);
    }


    /**
     * 分页查询维修项数据
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        //调用服务分页查询数据
        PageResult<Malfunction> pageResult = malfunctionService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_MALFUNCTION_SUCCESS, pageResult);
    }

    /**
     * 根据id删除检查项数据
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    @PreAuthorize("hasAnyAuthority('CHECKITEM_DELETE')")
    public Result deleteById(int id){
        //调用服务删除维修项数据,逻辑删除
        Malfunction malfunction = new Malfunction();
        malfunction.setId(id);
        malfunction.setIsDelete("1"); //1 已删除  0,未删除
        malfunctionService.update(malfunction);
        //返回响应
        return new Result(true, MessageConstant.DELETE_MALFUNCTION_SUCCESS);
    }

    /**
     * 根据id查询用户信息-编辑回显数据
     * @param id
     * @return
     */
    @GetMapping("/findById")
    @PreAuthorize("hasAnyAuthority('CHECKITEM_QUERY')")
    public Result findById(int id){
        //调用服务根据id查询用户信息
        Malfunction malfunction = malfunctionService.findById(id);
        //返回响应
        return new Result(true, "维修数据回显成功", malfunction);
    }


    /**
     * 修改维修项数据
     * @param malfunction
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('CHECKITEM_EDIT')")
    public Result update(@RequestBody Malfunction malfunction){
        //调用服务执行更新命令
        malfunctionService.update(malfunction);
        //返回响应
        return new Result(true, MessageConstant.EDIT_MALFUNCTION_SUCCESS);
    }

}
