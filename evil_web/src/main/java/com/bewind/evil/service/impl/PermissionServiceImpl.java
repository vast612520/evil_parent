package com.bewind.evil.service.impl;


import com.bewind.evil.constant.MessageConstant;

import com.bewind.evil.dao.PermissionDao;
import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.exception.EvilException;
import com.bewind.evil.pojo.Permission;
import com.bewind.evil.service.PermissionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {

        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() +"%");

        }
        Page<Permission> page = permissionDao.findPage(queryPageBean.getQueryString());

        PageResult<Permission> pageResult = new PageResult<Permission>(page.getTotal(),page.getResult());

        return pageResult;


    }

    @Override
    public void add(Permission permission) throws EvilException {
        int addnumber = permissionDao.add(permission);
        if(addnumber != 1){
            throw new EvilException(MessageConstant.ADD_PERMISSION_FAIL);
        }
    }

    @Override
    public Permission findById(int id) throws EvilException{

        Permission permission =  permissionDao.findById(id);
        if(permission == null){
            throw new EvilException(MessageConstant.QUERY_PERMISSION_FAIL);

        }
        return permission;

    }

    @Override
    public void update(Permission permission) throws EvilException {
        int updateNumber = permissionDao.update(permission);
        if(!( updateNumber > 0 )){
            throw new EvilException(MessageConstant.EDIT_PERMISSION_FAIL);
        }
    }

    @Override
    public void deleteById(int id) throws EvilException {
        int useNumber =  permissionDao.queryPermissionAndRole(id);
        if(useNumber == 0){
            int deleteNumber = permissionDao.deleteById(id);
            if(!( deleteNumber > 0 )){
                throw new EvilException(MessageConstant.DELETE_PERMISSION_FAIL);
            }
        }else{
            throw new EvilException("权限数据已被应用,无法删除");
        }


    }

    @Override
    public List<Permission> findAll() {

        List<Permission> list =  permissionDao.findAll();
        return list;
    }
}
