package com.cangwu.global;

import com.cangwu.config.RootConfig;
import com.cangwu.config.SecurityConfig;
import com.cangwu.config.SpringConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Author: Cangwu
 * @Date: 2019/4/29 20:04
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { SpringConfig.class,RootConfig.class };
    }
}
