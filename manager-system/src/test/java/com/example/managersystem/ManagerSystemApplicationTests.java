package com.example.managersystem;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.managersystem.entity.Admin;
import com.example.managersystem.mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ManagerSystemApplicationTests {

    @Autowired
    private AdminMapper userMapper;

    @Test
    void findAll() {
        List<Admin> userList = userMapper.selectList(null);
        System.out.println(userList);
    }


    @Test
    void insert() {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();//加密用encode，非加密用
        Admin user = new Admin();
        user.setId(3);
        user.setUsername("admin");
        user.setPassword(bcryptPasswordEncoder.encode( "123"));
        user.setPhone("13133838216");
        user.setEmail("dongfang@qq.com");
        user.setRid(1);
        user.setStatus(1);
        user.setCreatetime("2019-07-18");
        int i = userMapper.insert(user);
        System.out.println(i);
    }

    @Test
    void update() {
        //修改id为3的用户email为992116519@qq.com
        Admin user = new Admin();
        user.setId(3);
        user.setEmail("992116519@qq.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    //测试乐观锁
    @Test
    void testOptimisticLocker() {
        //根据id查询数据
        Admin user = userMapper.selectById(1);
//        user.setAge(100000);
        int i = userMapper.updateById(user);
        System.out.println(i);
        //版本号加1 ,乐观锁生效
    }

    //多个id批量查询
    @Test
    void selectBatchsIds() {
        List<Admin> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    @Test
    void testPage() {
        //创建page对象, 传入两个参数,当前页和每页显示记录数
        Page<Admin> page = new Page<>(1,2);
        //把分页的所有数据封装到page对象里面
        userMapper.selectPage(page, null);
        System.out.println(page.getRecords());
    }

    //删除  物理删除
    @Test
    void delete() {
        //删除单个
        int i = userMapper.deleteById(3);
        System.out.println(i);
        //批量删除
//        int i1 = userMapper.deleteBatchIds(Arrays.asList(2L, 3L));
//        System.out.println(i1);
    }


    //删除  逻辑删除
    @Test
    void logicDelete() {
        //执行的操作: UPDATE user SET deleted=1 WHERE id=? AND deleted=0
        int i = userMapper.deleteById(3);
        System.out.println(i);
    }


    //----------------------mybatis-plus实现复杂查询
    @Test
    void testSelectQuery() {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        //设置条件
        //ge大于等于  gt大于 le小于等于 lt小于
        //查询age>=30
//        queryWrapper.ge("age", 30);
//        List<User> users = userMapper.selectList(queryWrapper);
//        System.out.println(users);
        //eq等于   ne不等于 <>
//        queryWrapper.eq("name","东方不败");
//        List<User> users = userMapper.selectList(queryWrapper);
//        System.out.println(users);
//        queryWrapper.ne("name","东方不败");
//        List<User> users = userMapper.selectList(queryWrapper);
//        System.out.println(users);

//        LIKE 模糊查询'%值%'
//        queryWrapper.like("name","东方");
//        List<User> users = userMapper.selectList(queryWrapper);
//        System.out.println(users);

//        BETWEEN 值1 AND 值2
//        queryWrapper.between("age", 20,30);
//        List<User> users = userMapper.selectList(queryWrapper);
//        System.out.println(users);

//        orderByDesc降序排列
//        queryWrapper.orderByDesc("id");
//        List<User> users = userMapper.selectList(queryWrapper);
//        System.out.println(users);

        //last: 无视优化规则直接拼接到 sql 的最后
        queryWrapper.last("limit 1");
//        List<User> users = userMapper.selectList(queryWrapper);
//        System.out.println(users);

        //select : 指定要查询的列
        queryWrapper.select("id","name");
        List<Admin> users = userMapper.selectList(queryWrapper);
        System.out.println(users);

    }
}
