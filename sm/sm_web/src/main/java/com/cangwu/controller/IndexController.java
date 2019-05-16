package com.cangwu.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Cangwu
 * @Date: 2019/5/12 14:42
 */
@Controller("indexController")
public class IndexController {

    private UserDetails getUserDetails() {
        UserDetails userDetails = (UserDetails)
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();
        return userDetails;
    }

    /**
     * 显示主页
     * 获取已登录用户用户名
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model model) {
        UserDetails userDetails = getUserDetails();
        model.addAttribute("userSession",userDetails.getUsername());
        return "index";
    }

    /**
     * 显示主页左边的窗口
     * @return
     */
    @RequestMapping("/leftIndex")
    public String toLeft() { return "leftIndex"; }

    /**
     * 右边窗口跳转到查询部门页面
     * @return
     */
    @RequestMapping("/getADept")
    public String selectOneDept() { return "selectOneDept"; }

    /**
     * 右边窗口跳转到添加部门页面
     * @return
     */
    @RequestMapping("/addADept")
    public String addOneDept() { return "addOneDept"; }

    @RequestMapping("/accessError")
    public String accessErrorPage() { return "errorPage"; }

    @RequestMapping("/toLogin")
    public String toLogin() { return "login"; }

}
