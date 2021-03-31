package com.bewind.evil.service.impl;

import com.bewind.evil.dao.UserDao;
import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.pojo.User;
import com.bewind.evil.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：用户数据维护
 * User：JuZhao
 * Date：2020-11-06
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    /**
     * 根据登陆用户名称查询用户权限信息
     *
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        //调用dao根据登陆用户名称查询用户权限信息
        User byUsername = userDao.findByUsername(username);
        return byUsername;
    }

    /**
     * 分页查询用户
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<User> findPage(QueryPageBean queryPageBean) {
        //调用PageHelper插件启动分页
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //判断是否有查询条件
        if (null != queryPageBean.getQueryString()) {
            //若有查询条件，拼接 %
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        //调用持久层findPage方法，返回Page<User>方法
        Page<User> page = userDao.findPage(queryPageBean.getQueryString());
        //封装PageResult<User>并返回
        PageResult<User> pageResult = new PageResult<User>(page.getTotal(), page.getResult());
        return pageResult;
    }

    /**
     * 新增用户
     * @param user
     */
    @Override
    public void addUser(User user) {
        //调用UserDao的addUser方法，参数传入User

        userDao.addUser(user);
    }

    /**
     * 根据用户id查询关联的角色id
     * @param id
     * @return
     */
    @Override
    public List<Integer> checkRoleIdByUserId(int id) {
        return userDao.checkRoleIdByUserId(id);
    }

    @Override
    public void UpdateRoleIdByUserId(Integer[] roleIds, Integer userId) {
        //调用UserDao的deleteRoleUser方法，传入用户id
        userDao.deleteRoleUser(userId);
        //判断是否有关联角色
        if (null != roleIds) {
            //若有关联角色，遍历角色id数组，调用UserDao的addRoleUser方法
            for (Integer roleId : roleIds) {
                userDao.addRoleUser(roleId, userId);
            }
        }
    }

    /**
     * 根据用户id查询用户数据
     * @param userId
     * @return
     */
    @Override
    public User findById(int userId) {
        //调用UserDao的findById方法，参数用户id，返回User
        return userDao.findById(userId);
    }

    /**
     * 更新用户数据
     * @param user
     */
    @Override
    public int updateUser(User user) {
        //调用UserDao的updateUser方法，传入User
      return  userDao.updateUser(user);
    }

    /**
     * 删除用户
     * @param userId
     */
    @Override
    public void deleteById(int userId) {
        //调用userDao的deleteRoleUser方法,参数传入用户id
        userDao.deleteRoleUser(userId);
        //调用UserDao的deleteById方法，参数传入用户id
        userDao.deleteById(userId);
    }

    /**
     * 查询用户密码
     * @param userName
     * @return
     */
    @Override
    public String queryPassword(String userName) {
       String password = userDao.selectPassWord(userName);
        return password;
    }
}
