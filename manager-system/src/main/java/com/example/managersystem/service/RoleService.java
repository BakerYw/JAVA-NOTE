package com.example.managersystem.service;

import com.example.managersystem.common.result.PageBean;
import com.example.managersystem.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cheese
 * @since 2021-01-06
 */
public interface RoleService {
    List<Role> selectAll();

    PageBean<Role> queryPage(Map<String, Object> paramMap);

    int delByRoleIds(List<Integer> ids);

    Role selectById(Integer id);

    Role selectByName(String name);

    int editByRole(Role role);

    int insertRole(Role role);
}
