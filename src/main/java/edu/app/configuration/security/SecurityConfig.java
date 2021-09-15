package edu.app.configuration.security;

import edu.app.model.role.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;


@Controller
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(@Qualifier("UserDetailServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/home", "/films", "/registration").permitAll()
                .antMatchers("/profile/**").hasRole(RoleEnum.USER.name())
                .antMatchers("admin/**").hasRole(RoleEnum.ADMIN.name())
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .formLogin()
                .loginPage("/Login").permitAll()
                .defaultSuccessUrl("/home")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/home")
                .and()
                .rememberMe().userDetailsService(userDetailsService).tokenValiditySeconds(100000);
    }


    private AccessDeniedHandler accessDeniedHandler() { return null; }

    @Bean
    public  DaoAuthenticationProvider daoAuthenticationProvider () {
        DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
        daoAuth.setPasswordEncoder(passwordEncoder());
        daoAuth.setUserDetailsService(this.userDetailsService);
        return daoAuth;
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


}
