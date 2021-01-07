package com.example.managersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectOne;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.managersystem.common.result.PageBean;
import com.example.managersystem.entity.Admin;
import com.example.managersystem.entity.Log;
import com.example.managersystem.entity.Role;
import com.example.managersystem.mapper.RoleMapper;
import com.example.managersystem.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectList(null);
    }

    @Override
    public PageBean<Role> queryPage(Map<String, Object> paramMap) {
        PageBean<Role> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));
        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<Role> datas = roleMapper.selectByMap(paramMap);
        pageBean.setDatas(datas);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(paramMap);
        Integer totalsize = roleMapper.selectCount(queryWrapper);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }


    @Override
    public int delByRoleIds(List<Integer> ids) {
        try {
            return roleMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            //存在管理员拥有此角色，无法删除
            return 0;
        }
    }

    @Override
    public Role selectById(Integer id) {
        return roleMapper.selectById(id);
    }

    @Override
    public Role selectByName(String name) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return roleMapper.selectOne(queryWrapper);
    }

    @Override
    public int editByRole(Role role) {
        return roleMapper.update(role,null);
    }

    @Override
    public int insertRole(Role role) {
        return roleMapper.insert(role);
    }
}
