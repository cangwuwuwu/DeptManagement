package com.cangwu.controller;

import com.cangwu.entity.Log;
import com.cangwu.entity.Staff;
import com.cangwu.service.LogService;
import com.cangwu.service.SelfService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/5/12 12:24
 */
@Controller("selfController")
public class SelfController {

    @Resource
    private SelfService selfService;
    @Resource
    private LogService logService;

    private Staff user = null;

    /**
     * 登录错误/登出
     * @param error 登录错误信息
     * @param logout 登出信息
     * @return
     */
    @RequestMapping(value = "/submit")
    public String login(@RequestParam(value = "error",required = false) String error,
                        @RequestParam(value = "logout",required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("msg","账号或密码错误！");
        }
        if (logout != null) {
            model.addAttribute("msg","你已成功登出");
        }
        user = null;
        return "login";
    }

    @RequestMapping("/changePwd")
    public String toChangePsw(Principal principal, Model model) {
        String account = principal.getName();
        if (user == null) {
            user = selfService.findUserByAccount(account);
        }
        model.addAttribute("userAccount", user.getAccount());
        return "changePwd";
    }

    /**
     * 显示个人信息 操作日志
     * @return
     */
    @RequestMapping("/toPersion")
    public void toPersion(Principal principal, HttpServletResponse response) throws IOException {
        String account = principal.getName();
        if (user == null) {
            user = selfService.findUserByAccount(account);
        }
        Integer id = user.getId();
        response.sendRedirect("detail?id=" + id);
    }

    // 修改密码 操作日志
    @RequestMapping("/submitChangePwd")
    public String operateSubmitNewPwd(HttpServletRequest request,
                             Principal principal, Model model)
            throws IOException, ServletException {
        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");
        String account = principal.getName();
        if (user == null) {
            user = selfService.findUserByAccount(account);
        }
        if (user.getPassword().equals(oldPwd)) {
            selfService.changePassword(user.getId(),newPwd);
            model.addAttribute("changePwd","修改成功,点此重新登陆");
            model.addAttribute("classInfo","success");
            return "changePwd";
        } else {
            model.addAttribute("changePwd","请输入正确的旧密码");
            model.addAttribute("classInfo","error");
            return "changePwd";
        }
    }


    @RequestMapping("/toOperateLog")
    public String toOperateLogPage(HttpServletRequest request) {
        List<Log> list = logService.getOperationLog();
        request.setAttribute("operateLogs",list);
        return "operateLogPage";
    }

    @RequestMapping("/toSystemLog")
    public String toSystemLogPage(HttpServletRequest request) {
        List<Log> list = logService.getSystemLog();
        request.setAttribute("systemLogs",list);
        return "systemLogPage";
    }
}
