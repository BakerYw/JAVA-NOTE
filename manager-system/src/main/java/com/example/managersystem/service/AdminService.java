package com.example.managersystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.managersystem.common.result.PageBean;
import com.example.managersystem.entity.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

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
public interface AdminService extends UserDetailsService {

    PageBean<Admin> queryPage(Map<String, Object> paramMap);

    int insertAdmin(Admin admin);

    Admin selectById(Integer id);

    int delByAdminIds(List<Integer> ids);

    int editByAdmin(Admin admin);

    Admin selectByName(String username);

    Admin selectByEmail(String email);
}
