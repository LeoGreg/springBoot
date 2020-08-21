package com.example.demo.configs;


import com.example.demo.util.constants.Constants;
import com.example.demo.util.encoder.CustomEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/*  , securedEnabled = true, prePostEnabled = true*/
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class AdminSecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("detailImpl")
    private UserDetailsService UserDetailServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/adm/**")
                .httpBasic().and()
                .csrf().disable()
                .cors().disable()
                .headers().frameOptions().disable()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/adm/admin/**").hasAuthority(Constants.ADMIN)
                .antMatchers(HttpMethod.DELETE).hasAuthority(Constants.ADMIN)
                .antMatchers("/adm/**").authenticated();
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.

                inMemoryAuthentication()
                .withUser("admin").password("adminPassword").authorities(Constants.ADMIN).and()
                .passwordEncoder(CustomEncoder.getInstance());
    }


}
