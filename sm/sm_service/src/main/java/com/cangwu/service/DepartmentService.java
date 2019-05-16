package com.cangwu.service;

import com.cangwu.entity.Department;

import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/5/8 18:35
 */
public interface DepartmentService {
    void add(Department department);
    void remove(Integer id);
    void edit(Department department);
    Department get(Integer id);
    List<Department> getAll();
}
