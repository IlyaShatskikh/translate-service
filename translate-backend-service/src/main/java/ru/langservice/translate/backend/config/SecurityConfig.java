package ru.langservice.translate.backend.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.langservice.translate.backend.security.JwtAuthenticationFilter;
import ru.langservice.translate.backend.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .cors()
                .and()
                    .csrf()
                    .disable()

                    .authorizeRequests()
//                    .antMatchers("/", "/registration", "/css/**").permitAll()
                    .anyRequest().authenticated()

                .and()
                    .addFilter(new JwtAuthenticationFilter(authenticationManager()))

//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    .failureUrl("/login?error=true")
//                .and()
//                    .rememberMe()

                    .logout()
                    .permitAll()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }


}
