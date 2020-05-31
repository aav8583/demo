package com.securitydb.demo.config;

import com.securitydb.demo.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.securitydb.demo.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    AuthProviderImpl authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sign_up","/sign_in","/login").anonymous()
                .antMatchers("/greeting").authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/sign_in")
                .loginProcessingUrl("/sign_in/process")
                .usernameParameter("nameOfUser")
                .passwordParameter("password")
                .and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Autowired
    public void setAuthProvider(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }
}
