package com.cangwu.service;

import com.cangwu.entity.Staff;

import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/5/9 20:31
 */
public interface StaffService {
    void add(Staff staff);
    void remove(Integer id);
    void lWork(Staff staff);
    void edit(Staff staff);
    Staff get(Integer id);
    List<Staff> getAll();
}
