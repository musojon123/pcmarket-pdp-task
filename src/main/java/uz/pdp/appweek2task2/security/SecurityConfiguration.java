package uz.pdp.appweek2task2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("super_admin").password(passwordEncoder().encode("superUser")).roles("SUPER_USER").authorities("READ_ALL_PRODUCT", "ADD_PRODUCT", "EDIT_PRODUCT", "DELETE_PRODUCT", "READ_ONE_PRODUCT")
                .and()
                .withUser("moderator").password(passwordEncoder().encode("moderator")).roles("MODERATOR").authorities("ADD_PRODUCT", "EDIT_PRODUCT")
                .and()
                .withUser("operator").password(passwordEncoder().encode("operator")).roles("OPERATOR").authorities("READ_ALL_PRODUCT", "ADD_PRODUCT", "READ_ONE_PRODUCT");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
