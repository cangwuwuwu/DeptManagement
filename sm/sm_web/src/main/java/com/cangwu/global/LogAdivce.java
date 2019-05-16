package com.cangwu.global;

import com.cangwu.entity.Log;
import com.cangwu.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @Author: Cangwu
 * @Date: 2019/5/15 18:22
 */
@Component
@Aspect
public class LogAdivce {
    @Autowired
    private LogService logService;

    private String getUserDetails() {
        UserDetails userDetails = (UserDetails)
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();
        return userDetails.getUsername();
    }

    @Pointcut("execution(* com.cangwu.controller.*.operate*(..))")
    public void operatepoint() { }
    /*@Pointcut("execution(* com.cangwu.controller.*.login*(..))")
    public void loginpoint() { }*/
    @Pointcut("execution(* com.cangwu.controller.*.*(..))")
    public void systempoint() { }

    @After("operatepoint()")
    public void operationLog(JoinPoint joinPoint) {
        Log log = new Log();
        // 模块名
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        // 当前操作
        log.setOperation(joinPoint.getSignature().getName());
        String account = getUserDetails();
        log.setOperator(account);
        log.setResult("成功");
        logService.addOperationLog(log);

    }

    /*@After("loginpoint()")
    public void loginLog(JoinPoint joinPoint) {

    }*/

    @AfterThrowing(throwing = "e", pointcut = "systempoint()")
    public void systemLog(JoinPoint joinPoint,Throwable e) {
        Log log = new Log();
        log.setOperation(joinPoint.getSignature().getName());
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        String account = getUserDetails();
        log.setOperator(account);
        log.setResult(e.getClass().getSimpleName());
        logService.addSystemLog(log);
    }
}
