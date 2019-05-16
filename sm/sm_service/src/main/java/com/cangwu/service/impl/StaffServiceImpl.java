package com.cangwu.service.impl;

import com.cangwu.entity.Staff;
import com.cangwu.mapper.StaffMapper;
import com.cangwu.service.StaffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/5/9 20:36
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {

    @Resource
    private StaffMapper staffMapper;

    public void add(Staff staff) {
        // 默认密码
        staff.setPassword("123456");
        // 入职时间 系统当前时间
        staff.setWorkTime(new Date());
        //设置状态
        staff.setStatus("正常");
        staffMapper.insert(staff);
    }

    public void remove(Integer id) {
        staffMapper.delete(id);
    }

    public void lWork(Staff staff) {
        staff.setLeaveTime(new Date());
        staff.setStatus("离职");
        staffMapper.leaveWork(staff);
    }

    public void edit(Staff staff) {
        staffMapper.update(staff);
    }

    public Staff get(Integer id) {
        return staffMapper.selectById(id);
    }

    public List<Staff> getAll() {
        return staffMapper.selectAll();
    }
}
