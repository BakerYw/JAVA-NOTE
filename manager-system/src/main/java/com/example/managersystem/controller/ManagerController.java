package com.example.managersystem.controller;


import com.example.managersystem.common.result.AjaxResult;
import com.example.managersystem.common.utils.Const;
import com.example.managersystem.entity.Admin;
import com.example.managersystem.entity.TreeMenu;
import com.example.managersystem.service.TreeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cheese
 * @since 2021-01-06
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private TreeMenuService treeMenuService;

    /**
     * 跳转后台页面
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "manager/index";
    }

    /**
     * 异步加载权限树
     * @param session
     * @return
     */
    @PostMapping("/treeMenu")
    @ResponseBody
    public Object treeMenu(HttpSession session){
        if(!StringUtils.isEmpty(session.getAttribute(Const.TREEMENU))){
            return session.getAttribute(Const.TREEMENU);
        }
        Admin admin = (Admin) session.getAttribute(Const.ADMIN);
        List<TreeMenu> treeMenuList = treeMenuService.selectByAdminId(admin.getId());
        session.setAttribute(Const.TREEMENU,treeMenuList);
        return treeMenuList;
    }

    /**
     * 异步加载后台主页
     * @return
     */
    @GetMapping("/console")
    public String console(){
        return "manager/console";
    }

}


