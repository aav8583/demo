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
                .antMatchers("/sign_up", "/sign_in", "/login").anonymous()
                .antMatchers("/greeting").authenticated()
                .and().csrf().disable()
                .formLogin()
                .defaultSuccessUrl("/login", true)
                .loginProcessingUrl("/sign_in")
                .usernameParameter("nameOfUser")
                .passwordParameter("password")
                .and().logout();  //здесь добавить авторизацию по ролям.
    }

//            http.authorizeRequests()
//                    .antMatchers("/", "/registration", "/static/**", "/error").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .defaultSuccessUrl("/",true)
//                    .permitAll()
//                    .and()
//                    .logout()
//                    .permitAll()
//                    .logoutSuccessUrl("/");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Autowired
    public void setAuthProvider(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }
}
