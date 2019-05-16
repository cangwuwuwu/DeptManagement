package com.cangwu.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author: Cangwu
 * @Date: 2019/5/7 22:50
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.cangwu")
@EnableAspectJAutoProxy
/*@EnableWebSecurity*/
@EnableTransactionManagement
@Import({TransactionConfig.class, ThymeleafConfig.class})
@ImportResource("classpath:spring.xml")
public class SpringConfig {
}
