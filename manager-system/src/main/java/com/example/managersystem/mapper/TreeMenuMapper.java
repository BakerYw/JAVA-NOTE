package com.example.managersystem.mapper;

import com.example.managersystem.entity.TreeMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
public interface TreeMenuMapper extends BaseMapper<TreeMenu> {

    @Select("  select treemenu.id,treemenu.pid,treemenu.name,treemenu.icon,treemenu.url" +
            "        from admin,role,role_treemenu,treemenu" +
            "        where admin.rid = role.id" +
            "        and role.id = role_treemenu.rid" +
            "        and treemenu.id = role_treemenu.tid" +
            "        and admin.id = #{id}")

    List<TreeMenu> selectByAdminId(@Param("id") Integer id);




    @Select("  select treemenu.url" +
            "        from admin,role,role_treemenu,treemenu" +
            "        where admin.rid = role.id" +
            "        and role.id = role_treemenu.rid" +
            "        and treemenu.id = role_treemenu.tid" +
            "        and treemenu.pid !=  -1" +
            "        and admin.username = #{username}")
    List<String> selectByUserName(@Param("username") String username);





    @Select("select treemenu.url from treemenu")
    List<String> selectURLAll();


    @Select(" select * from treemenu")
    List<TreeMenu> selectAll();
}
