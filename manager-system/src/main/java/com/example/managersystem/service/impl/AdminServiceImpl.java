package com.example.managersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.managersystem.common.result.PageBean;
import com.example.managersystem.entity.Admin;
import com.example.managersystem.mapper.AdminMapper;
import com.example.managersystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cheese
 * @since 2021-01-06
 */
@Service
public class AdminServiceImpl implements AdminService{


    @Autowired
    private AdminMapper adminMapper;


    @Override
    public PageBean<Admin> queryPage(Map<String, Object> paramMap) {
        PageBean<Admin> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));
        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<Admin> datas = adminMapper.selectByMap(paramMap);
        pageBean.setDatas(datas);
        Integer totalsize = adminMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int insertAdmin(Admin admin) {
        return adminMapper.insert(admin);
    }

    @Override
    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }

    @Override
    public int delByAdminIds(List<Integer> ids) {
        return adminMapper.deleteBatchIds(ids);
    }

    @Override
    public int editByAdmin(Admin admin) {
        return adminMapper.update(admin,null);
    }

    @Override
    public Admin selectByName(String username) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return adminMapper.selectOne(queryWrapper);
    }

    @Override
    public Admin selectByEmail(String email) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        return adminMapper.selectOne(queryWrapper);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return adminMapper.findByName(s);
    }
}
