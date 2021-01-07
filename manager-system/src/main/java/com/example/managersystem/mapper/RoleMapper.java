package com.example.managersystem.mapper;

import com.example.managersystem.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cheese
 * @since 2021-01-06
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select role.id,role.name from role,admin where admin.rid = role.id and admin.id = #{adminId}")
    List<Role> findByAdminId(Integer adminId);

    @Select("select role.id,role.name from role,role_treemenu,treemenu where role.id = role_treemenu.rid " +
            "and role_treemenu.tid = treemenu.id and treemenu.id = #{treeMenuId}")
    List<Role> findByTreeMenuId(Integer treeMenuId);
}
