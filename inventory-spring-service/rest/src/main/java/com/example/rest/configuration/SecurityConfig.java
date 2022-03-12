package com.example.rest.configuration;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//  The prePostEnabled property enables Spring Security pre/post annotations.
//  The securedEnabled property determines if the @Secured annotation should be enabled.
//  The jsr250Enabled property allows us to use the @RoleAllowed annotation.
//@Configuration
public class SecurityConfig //extends GlobalMethodSecurityConfiguration
{

    //@Bean
    public WebSecurityCustomizer ignoreResources() {
        return (webSecurity) -> webSecurity
                .ignoring()
                .antMatchers("/login2/*", "/health/*");
    }

    //@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
