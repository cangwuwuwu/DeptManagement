package com.cangwu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author: Cangwu
 * @Date: 2019/5/14 7:21
 */
@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableAspectJAutoProxy
@ComponentScan("com.cangwu")
@Import({SecurityConfig.class})
public class RootConfig {
}
