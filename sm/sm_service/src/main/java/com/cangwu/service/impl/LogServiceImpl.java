package com.cangwu.service.impl;

import com.cangwu.entity.Log;
import com.cangwu.mapper.LogMapper;
import com.cangwu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/5/15 15:21
 */
@Service("logService")
public class LogServiceImpl implements LogService{

    @Autowired
    private LogMapper logMapper;


    public void addSystemLog(Log log) {
        log.setOprTime(new Date());
        log.setType("system");
        logMapper.insertLog(log);
    }

    public void addLoginLog(Log log) {
        log.setType("login");
        log.setOprTime(new Date());
        logMapper.insertLog(log);
    }

    public void addOperationLog(Log log) {
        log.setType("operation");
        log.setOprTime(new Date());
        logMapper.insertLog(log);
    }

    public List<Log> getSystemLog() {
        return logMapper.selectByType("system");
    }

    public List<Log> getLoginLog() {
        return logMapper.selectByType("login");
    }

    public List<Log> getOperationLog() {
        return logMapper.selectByType("operation");
    }
}
