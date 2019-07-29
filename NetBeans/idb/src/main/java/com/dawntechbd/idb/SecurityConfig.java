package com.dawntechbd.idb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //    @Autowired
//    private LoggingAccessDeniedHandler loggingAccessDeniedHandler;
//
//    @Autowired
//    CustomUserDetailsService customUserDetailsService;
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("shuvo")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN", "USER")
                .and()
                .withUser("rony")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .and()
                .withUser("yasin")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN");
    }

//    @Bean(BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    //    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("shuvo")
//                .password("1234")
//                .roles("ADMIN", "USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers(
                        "/", "/login", "/user/add"
                ).permitAll()
                .antMatchers(
                         "/user/edit/{id}**", "/role"
                ).hasRole("ADMIN")
                .antMatchers("/user/list/**").hasRole(
                "USER")
//                .antMatchers("/mn/**").hasRole(
//                "MANAGER").antMatchers("/u/**").hasRole(
//                "USER")
//                .antMatchers("/se/**").hasAnyRole(
//                "ADMIN", "USER", "SUPERADMIN", "MANAGER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling();
    }


}
