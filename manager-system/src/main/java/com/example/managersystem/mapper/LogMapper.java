package com.example.managersystem.mapper;

import com.example.managersystem.entity.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

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
public interface LogMapper extends BaseMapper<Log> {


    @Select("<script>" +
            " select *  from log where true " +
            "<if test=\"username!=null\"> " +
            " and username = #{username} " +
            "</if>" +
            "<if test=\"stime!=null and ftime!=null\"> " +
            " and logTime &gt;= #{stime} and logTime &lt;= #{ftime} " +
            "</if>" +
            " limit #{startIndex},#{pagesize}"+
            "</script>")
    List<Log> queryList(Map<String, Object> paramMap);


    @Select("<script>" +
            " select count(*) from log WHERE true " +
            "<if test=\"username!=null\"> " +
            " username = #{username} " +
            "</if>" +
            "<if test=\"stime!=null and ftime!=null\"> " +
            " and logTime &gt;= #{stime} and logTime &lt;= #{ftime} " +
            "</if>" +
            "</script>")
    Integer queryCount(Map<String, Object> paramMap);
}
