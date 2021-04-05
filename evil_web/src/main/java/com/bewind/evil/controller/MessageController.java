package com.bewind.evil.controller;



import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.entity.Result;

import com.bewind.evil.pojo.Message;

import com.bewind.evil.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

   @Autowired
   private MessageService messageService;

    /**
     * 查询所有的留言
     * @return
     */
    @RequestMapping("/findAll")
    @PreAuthorize("hasAnyAuthority('MESSAGE_QUERY')")
    public Result findAll(){
        //调用服务查询留言数据
        List<Message> list = messageService.findAll();
        return new Result(true, "操作成功", list);
    }

    /**
     * 新增留言数据
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('MESSAGE_ADD')")
    public Result add(@RequestBody Message message){
        //获取用户名
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        message.setCreated(user.getUsername()+"");
        //调用服务新增留言
        messageService.add(message);
        return new Result(true, "留言成功,已推入审批池");
    }


    /**
     * 分页查询维修项数据
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    @PreAuthorize("hasAuthority('MESSAGE_QUERY')")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        //调用服务分页查询数据
        PageResult<Message> pageResult = messageService.findPage(queryPageBean);
        return new Result(true, "查询留言成功", pageResult);
    }

    /**
     * 根据id删除留言
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    @PreAuthorize("hasAnyAuthority('MESSAGE_DELETE')")
    public Result deleteById(int id){
        //调用服务删除维修项数据,逻辑删除
        Message message = new Message();
        message.setId(id);
        message.setIsDelete("1"); //1 已删除  0,未删除
        messageService.update(message);
        //返回响应
        return new Result(true, "删除成功");
    }

    /**
     * 根据id查询用户信息-编辑回显数据
     * @param id
     * @return
     */
    @GetMapping("/findById")
    @PreAuthorize("hasAnyAuthority('MESSAGE_QUERY')")
    public Result findById(int id){
        //调用服务根据id查询用户信息
        Message message = messageService.findById(id);
        //返回响应
        return new Result(true, "留言回显成功", message);
    }


    /**
     * 修改维修项数据
     * @param message
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('MESSAGE_EDIT')")
    public Result update(@RequestBody Message message){
        //调用服务执行更新命令
        messageService.update(message);
        //返回响应
        return new Result(true, "修改留言成功");
    }

}
