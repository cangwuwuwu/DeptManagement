package com.cangwu.service;

import com.cangwu.entity.Staff;

/**
 * @Author: Cangwu
 * @Date: 2019/5/12 10:45
 */
public interface SelfService {
    Staff login(String account,String password);
    void changePassword(Integer id,String newPwd);
    Staff findUserByAccount(String account);
}
