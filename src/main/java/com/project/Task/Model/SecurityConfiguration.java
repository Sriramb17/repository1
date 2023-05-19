//package com.project.Task.Model;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfiguration extends GlobalMethodSecurityConfiguration {
////    @Secured("ROLE_VIEWER")
////    public String getUserName(){
////        SecurityContext securityContext= SecurityContextHolder.getContext();
////        return securityContext.getAuthentication().getName();
////
//
//    protected void configure(HttpSecurity http) throws Exception{
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/","/").permitAll()
//                .antMatchers("/").hasRole("SUPERADMIN")
//                .antMatchers("/").hasAnyRole("ADMIN","USER")
//                .and()
//                .httpBasic();
//    }
//
//}
