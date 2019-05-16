package com.cangwu.service.impl;

import com.cangwu.entity.Staff;
import com.cangwu.mapper.SelfMapper;
import com.cangwu.mapper.StaffMapper;
import com.cangwu.service.SelfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Cangwu
 * @Date: 2019/5/12 10:48
 */
@Service("selfService")
public class SelfServiceImpl implements SelfService {

    @Resource
    private SelfMapper selfMapper;
    @Resource
    private StaffMapper staffMapper;

    public Staff login(String account, String password) {
        Staff staff = selfMapper.selectByAccount(account);
        if (staff.getPassword().equals(password)) { return staff; }
        return null;
    }

    public void changePassword(Integer id, String newPwd) {
        Staff staff = staffMapper.selectById(id);
        staff.setPassword(newPwd);
        staffMapper.update(staff);
    }

    public Staff findUserByAccount(String account) {
        Staff staff = selfMapper.selectByAccount(account);
        return staff;
    }
}
