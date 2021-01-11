package com.example.managersystem.mapper;

import com.example.managersystem.entity.TreeMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

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


    @Select("select * from treemenu where id = #{id}")
    TreeMenu selectByid(Integer id);

    @Select(" select * from treemenu where pid = #{id}")
    List<TreeMenu> selectByPid(Integer id);

    @Select("  select treemenu.url" +
            "        from admin,role,role_treemenu,treemenu" +
            "        where admin.rid = role.id" +
            "        and role.id = role_treemenu.rid" +
            "        and treemenu.id = role_treemenu.tid" +
            "        and treemenu.pid !=  -1" +
            "        and admin.username = #{username}")
    List<String> selectByUserName(@Param("username") String username);



    @Select(" select * from treemenu where name =#{name}")
    TreeMenu selectByName(String name);


    @Select(" select * from treemenu where url =#{url}")
    TreeMenu selectByUrl(String url);



    @Select("select treemenu.url from treemenu")
    List<String> selectURLAll();


    @Select(" select * from treemenu")
    List<TreeMenu> selectAll();


    @Select("  select treemenu.id,treemenu.pid,treemenu.name,treemenu.icon,treemenu.url" +
            "        from role,role_treemenu,treemenu" +
            "        where role.id = role_treemenu.rid and treemenu.id = role_treemenu.tid and role.id = #{id}")
    List<TreeMenu> selectByRoleId(Integer id);


    @Update("update treemenu set name = #{name},url = #{url}  where id = #{id}")
    int editByPermission(TreeMenu treeMenu);




    @Insert("<script>" +
            "insert into treemenu"+
            "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">" +
            "            <if test=\"id != null\">" +
            "                id," +
            "            </if>" +
            "            <if test=\"pid != null\">" +
            "                pid," +
            "            </if>" +
            "            <if test=\"name != null\">" +
            "                name," +
            "            </if>" +
            "            <if test=\"icon != null\">" +
            "                icon," +
            "            </if>" +
            "            <if test=\"url != null\">" +
            "                url," +
            "            </if>" +
            "        </trim>" +
            "        <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">" +
            "            <if test=\"id != null\">" +
            "                #{id}," +
            "            </if>" +
            "            <if test=\"pid != null\">" +
            "                #{pid}," +
            "            </if>" +
            "            <if test=\"name != null\">" +
            "                #{name}," +
            "            </if>" +
            "            <if test=\"icon != null\">" +
            "                #{icon}," +
            "            </if>" +
            "            <if test=\"url != null\">" +
            "                #{url}," +
            "            </if>" +
            "        </trim>"+
            "</script>")
    int insertPermission(TreeMenu treeMenu);

    @Delete("<script>" +
            " delete from role_treemenu where tid in" +
            "<foreach collection=\"list\" item=\"id\" separator=\",\" open=\"(\" close=\")\"> " +
            " #{id}" +
            "</foreach>"+
            "</script>")
    void delRolePermission(List<Integer> ids);


    @Delete("delete from role_treemenu where rid = #{id}")
    void delRolePermissionByRid(Integer id);


    @Insert("<script>" +
            " insert into role_treemenu(rid,tid) values" +
            "<foreach collection=\"ids\" index=\"index\" item=\"tid\" separator=\",\">" +
            " (#{id},#{tid})" +
            "</foreach>"+
            "</script>")
    int updateRolePermission(@Param("ids") List<Integer> ids, @Param("id") Integer id);


    @Delete("<script>"+
            " delete from treemenu where id in" +
            "<foreach collection=\"list\" item=\"id\" separator=\",\" open=\"(\" close=\")\">" +
            "            #{id}" +
            "</foreach>"+
            "</script>")
    int delByPermissionIds(List<Integer> ids);
}
