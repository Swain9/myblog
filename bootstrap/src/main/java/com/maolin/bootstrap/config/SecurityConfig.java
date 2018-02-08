package com.maolin.bootstrap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 *
 * @author 45022
 * @since 2017/11/10 22:14
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll() //都可以访问
                .antMatchers("/users/**").hasRole("ADMIN")  //需要相应的角色才能访问
                .and()
                .formLogin() //基于FORM表单登陆验证
                .loginPage("/login").failureForwardUrl("/login-error"); //自定义登陆界面
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() //认证信息存储于内存中
                .withUser("maolin").password("123456").roles("ADMIN");
    }
}
