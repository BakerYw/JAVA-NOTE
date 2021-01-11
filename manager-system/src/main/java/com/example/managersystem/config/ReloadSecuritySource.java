package com.example.managersystem.config;

import com.example.managersystem.entity.Role;
import com.example.managersystem.entity.TreeMenu;
import com.example.managersystem.mapper.RoleMapper;
import com.example.managersystem.mapper.TreeMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Auther: wdd
 * @Date: 2020/1/1 14:10
 * @Description:
 */
@Component
public class ReloadSecuritySource {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private TreeMenuMapper treeMenuMapper;

    public void getReloadSecuritySource(){
        Map<RequestMatcher, Collection<ConfigAttribute>> map = new HashMap<>();
        for(TreeMenu m : treeMenuMapper.selectAll()){
            if(!StringUtils.isEmpty(m.getUrl())){
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(m.getUrl());
                ArrayList<ConfigAttribute> configs = new ArrayList<>();
                for(Role r : roleMapper.findByTreeMenuId(m.getId())){
                    org.springframework.security.access.SecurityConfig config = new SecurityConfig(r.getName());
                    configs.add(config);
                }
                map.put(matcher,configs);
            }
        }
        new MyFilterSecurityMetadataSource(map);
    }
}
