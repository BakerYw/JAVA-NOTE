package com.example.managersystem.mapper;

import com.example.managersystem.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cheese
 * @since 2021-01-06
 */
public interface AdminMapper extends BaseMapper<Admin> {


    @Select("<script>" +
            " select admin.*,role.name as roleName from admin,role WHERE " +
            " admin.rid = role.id"+
            " <if test=\"username!=null\"> " +
            " and admin.username LIKE concat(\"%\",#{username},\"%\") " +
            "</if>" +
            "<if test=\"email!=null\">" +
            " and admin.email = #{email} " +
            "</if>" +
            "<if test=\"phone!=null\">" +
            " and admin.phone = #{phone} " +
            "</if>" +
            "<if test=\"rid!=null\">" +
            " and admin.rid = #{rid} " +
            "</if>" +
            " limit #{startIndex},#{pagesize}"+
            "</script>")
    List<Admin> queryList(Map<String, Object> paramMap);




    @Select("select * from admin where username = #{username}")
    @Results({
            @Result(id = true, property = "id",column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.example.managersystem.mapper.RoleMapper.findByAdminId"))
    })
    Admin findByName(String username);


}
