package com.cangwu.controller;

import com.cangwu.entity.Department;
import com.cangwu.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/4/29 22:11
 */
@Controller("departmentController")
public class DepartmentController{

    @Resource
    private DepartmentService departmentService;

    /******************************************************************查询信息*/
    @RequestMapping("/select")
    public String dept() {
        return "select";
    }

    // 查询一个部门 操作日志
    @RequestMapping(value = "/selectId",method = RequestMethod.POST)
    public String operateGetIdFromWeb(@RequestParam("inputId")Integer id, ModelMap modelMap) {
        Department department = departmentService.get(id);
        modelMap.addAttribute("selectOne",department);
        return "One";
    }

    // 查询所有部门
    @RequestMapping("/getAll")
    public String toList(Model model) {
        List<Department> list = departmentService.getAll();
        model.addAttribute("lists",list);
        return "dept";
    }

    /******************************************************************添加信息*/
    // 添加一个部门 操作日志
    @RequestMapping("/addInfo")
    public void operateAdd(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        String name = request.getParameter("addDName");
        String address = request.getParameter("addDAddress");

        // 封装成实体对象
        Department department = new Department();
        department.setName(name);
        department.setAddress(address);

        // 将信息插入到数据库
        departmentService.add(department);
        // 重定向到所有信息界面
        response.sendRedirect("getAll");
    }

    /******************************************************************修改信息*/
    /**
     * 修改部门信息 操作日志
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/editInfo")
    public void operateEdit(HttpServletRequest request ,
                            HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("editName");
        String address = request.getParameter("editAddress");

        Department department = new Department();
        department.setId(id);
        department.setName(name);
        department.setAddress(address);

        departmentService.edit(department);
        response.sendRedirect("getAll");
    }

    /**
     * 点击编辑按钮
     * 获取id对应name和address
     * 转发到edit.html
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String toEdit(HttpServletRequest request,Model model) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Department department = departmentService.get(id);
        model.addAttribute("editDepartment",department);
        return "edit";
    }

    /******************************************************************刪除信息*/
    /**
     * 删除部门 操作日志
     * @param request
     * @param response
     * @param model
     * @throws IOException
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public void operateDelete(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        departmentService.remove(id);
        response.sendRedirect("getAll");
    }
}
