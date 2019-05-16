package com.cangwu.controller;

import com.cangwu.entity.Department;
import com.cangwu.entity.Staff;
import com.cangwu.service.DepartmentService;
import com.cangwu.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/5/9 21:35
 */
@Controller("staffController")
public class StaffController {

    @Resource
    private StaffService staffService;
    @Resource
    private DepartmentService departmentService;

    /**
     * 代码重用
     * @param request http请求
     * @param way 传入add执行添加员工
     *            传入edit执行编辑员工
     */
    private void getParameter(HttpServletRequest request,String way) {
        String account = request.getParameter("Account");
        String name = request.getParameter("Name");
        String idNumber = request.getParameter("IdNumber");
        Integer did = Integer.parseInt(request.getParameter("Did"));
        String sex = request.getParameter("Sex");
        String info = request.getParameter("Info");
        String status = request.getParameter("Status");
        Date bornDate = null;
        try {
            bornDate = new SimpleDateFormat("yyyy-MM-dd")
                    .parse(request.getParameter("Born"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Staff staff = new Staff();
        staff.setName(name);
        staff.setAccount(account);
        staff.setStatus(status);
        staff.setDid(did);
        staff.setInfo(info);
        staff.setIdNumber(idNumber);
        staff.setSex(sex);
        staff.setBornDate(bornDate);

        if ("edit".equals(way)) {
            Integer id = Integer.parseInt(request.getParameter("StaffId"));
            staff.setId(id);
            staffService.edit(staff);
        } else if ("add".equals(way)){
            staffService.add(staff);
        }
    }

    private void selectDeptAndStaff(HttpServletRequest request,ModelMap modelMap) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffService.get(id);
        modelMap.addAttribute("Staff",staff);
        List<Department> list = departmentService.getAll();
        request.setAttribute("list",list);
    }

    /******************************************************************查询信息*/
    /** 查询所有员工信息 操作日志*/
    @RequestMapping("/getList")
    public String selectAllList(HttpServletRequest request,ModelMap modelMap) {
        List<Staff> list = staffService.getAll();
        modelMap.addAttribute("stafflists",list);
        List<Department> dlist = departmentService.getAll();
        request.setAttribute("dlist",dlist);
        return "stafflist";
    }

    /** 查看员工详情 操作日志*/
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public String operateSelectStaffDetail(HttpServletRequest request,ModelMap modelMap) {
        selectDeptAndStaff(request,modelMap);
        return "staffDetail";
    }

    /******************************************************************添加信息*/
    /**
     * 右边窗口跳转到添加员工页面
     * @return
     */
    @RequestMapping("/addAStaff")
    public String addOneStaff(HttpServletRequest request) {
        List<Department> dlist = departmentService.getAll();
        request.setAttribute("dlist",dlist);
        return "addOneStaff";
    }


    @RequestMapping("/addStaff")
    public void operateAddStaff(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        getParameter(request,"add");
        response.sendRedirect("getList");
    }

    /******************************************************************删除员工信息*/
    @RequestMapping("/deleteStaff")
    public void operateDeleteStaff(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        staffService.remove(id);
        response.sendRedirect("getList");
    }

    /***********************************************************************炒鱿鱼*/
    @RequestMapping(value = "/leaveWork",method = RequestMethod.GET)
    public void operateLeaveWork(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Staff lStaff = staffService.get(id);
        staffService.lWork(lStaff);
        response.sendRedirect("getList");
    }
    /******************************************************************编辑信息*/
    @RequestMapping(value = "/editStaff",method = RequestMethod.GET)
    public String editStaff(HttpServletRequest request,ModelMap modelMap) {
        selectDeptAndStaff(request,modelMap);
        return "editStaff";
    }

    @RequestMapping("/finishEditStaff")
    public void operateFinishEditStaff(HttpServletRequest request ,HttpServletResponse response) throws IOException {
        getParameter(request,"edit");
        response.sendRedirect("getList");
    }
}
