package com.bewind.evil.controller;



import com.bewind.evil.constant.MessageConstant;
import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.entity.Result;
import com.bewind.evil.pojo.Permission;
import com.bewind.evil.service.PermissionService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/permission")

public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /*分页查询*/
    @PostMapping("/findPage")
    @PreAuthorize("hasAnyAuthority('PERMISSION_QUERY')")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){

        PageResult pageResult =  permissionService.findPage(queryPageBean);

        return new Result(true, MessageConstant.QUERY_PERMISSION_SUCCESS,pageResult);

    }


        /*新增权限数据*/
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('PERMISSIONSETTING')")
    public Result add(@RequestBody Permission permission){

        permissionService.add(permission);
        return new Result(true,MessageConstant.ADD_PERMISSION_SUCCESS);
    }

        /*根据Id查询数据*/
    @GetMapping("/findById")
    @PreAuthorize("hasAnyAuthority('PERMISSION_QUERY')")
    public Result findById(@RequestParam int id){

        Permission permission =  permissionService.findById(id);
        return new Result(true,MessageConstant.QUERY_PERMISSION_SUCCESS,permission);

    }

        /*编辑权限数据*/
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('PERMISSIONSETTING')")
        public Result update(@RequestBody Permission permission){

        permissionService.update(permission);
        return  new Result(true,MessageConstant.EDIT_PERMISSION_SUCCESS);
    }

    /*根据id删除数据*/

    @RequestMapping("/deleteById")
    @PreAuthorize("hasAnyAuthority('PERMISSIONSETTING')")
    public Result deleteById(@RequestParam int id){

        permissionService.deleteById(id);
        return new Result(true,MessageConstant.DELETE_PERMISSION_SUCCESS);
    }

    /*查询所有权限*/

    @RequestMapping("/findAll")
    @PreAuthorize("hasAnyAuthority('PERMISSION_QUERY')")
    public Result findAll(){

        List<Permission> list =  permissionService.findAll();

        return new Result(true,MessageConstant.QUERY_PERMISSION_SUCCESS,list);

    }

}
