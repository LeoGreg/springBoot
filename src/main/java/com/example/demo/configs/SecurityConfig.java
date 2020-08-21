package com.example.demo.configs;
//
//import com.example.demo.util.constants.Constants;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.annotation.Priority;
//
//@Order//default lowest
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(jsr250Enabled = true/*,securedEnabled = true,prePostEnabled = true*/)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    @Qualifier("detailImpl")
//    private UserDetailsService UserDetailServiceImpl;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/api/**")
//                .httpBasic().and()
//                .csrf().disable()
//                .cors().disable()
//                .headers().frameOptions().disable()
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authorizeRequests()
//                .antMatchers("/api/accounts/**").hasAuthority(Constants.USER)
//                .antMatchers("/api/metadata/**").hasAuthority(Constants.USER)
//                .antMatchers("/api/files/**").hasAuthority(Constants.USER)
//                .antMatchers("/api/**").authenticated();
//
//    }
//
//
//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(UserDetailServiceImpl)
//                .passwordEncoder(passwordEncoder);
// /*
//    inMemoryAuthentication()
//                .withUser("user1").password("user1password").authorities("ROLE_USER").and()
//                .withUser("admin").password("adminPassword").authorities("ROLE_ADMIN").and()
//                .passwordEncoder(CustomEncoder.getInstance());*/
//    }
//
//
