package com.cangwu.service.impl;

import com.cangwu.mapper.DepartmentMapper;
import com.cangwu.entity.Department;
import com.cangwu.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/4/29 22:06
 */
@Service("departmentService")
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    public void add(Department department) {
        departmentMapper.insert(department);
    }

    public void remove(Integer id) {
        departmentMapper.delete(id);
    }

    public void edit(Department department) {
        departmentMapper.update(department);
    }

    public Department get(Integer id) {
        return departmentMapper.selectById(id);
    }

    public List<Department> getAll() {
        return departmentMapper.selectAll();
    }
}
