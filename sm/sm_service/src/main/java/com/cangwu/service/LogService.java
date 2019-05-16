package com.cangwu.service;

import com.cangwu.entity.Log;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/5/15 15:19
 */
public interface LogService {
    void addSystemLog(Log log);
    void addLoginLog(Log log);
    void addOperationLog(Log log);

    List<Log> getSystemLog();
    List<Log> getLoginLog();
    List<Log> getOperationLog();
}
