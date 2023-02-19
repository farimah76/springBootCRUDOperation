package com.javacrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@ComponentScan("com.javacrud")
public class WebSecurityConfig implements WebMvcConfigurer {

    @Bean
    protected InMemoryUserDetailsManager configAuthentication() {

        List<UserDetails> users = new ArrayList<>();
        List<GrantedAuthority> adminAuthority = new ArrayList<>();
        adminAuthority.add(new SimpleGrantedAuthority("ADMIN"));
        UserDetails admin = new User("devs", "{noop}devs", adminAuthority);
        users.add(admin);

        List<GrantedAuthority> employeeAuthority = new ArrayList<>();
        adminAuthority.add(new SimpleGrantedAuthority("EMPLOYEE"));
        UserDetails employee = new User("ns", "{noop}ns", employeeAuthority);
        users.add(employee);

        List<GrantedAuthority> managerAuthority = new ArrayList<>();
        adminAuthority.add(new SimpleGrantedAuthority("MANAGER"));
        UserDetails manager = new User("vs", "{noop}vs", managerAuthority);
        users.add(manager);

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().ignoringRequestMatchers("/h2-console/**");
        //declares which Page(URL) will have What access type
        http.authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers(HttpMethod.POST,"/h2-console/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET,"/h2-console/**").hasAuthority("ADMIN")
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/h2-console/**").authenticated()
                .requestMatchers("/welcome").authenticated()
                .requestMatchers("/book").hasAuthority("ADMIN")
                .requestMatchers("/books").hasAuthority("EMPLOYEE")
                .requestMatchers("/book/{bookid}").hasAuthority("MANAGER")

                // Any other URLs which are not configured in above antMatchers
                // generally declared aunthenticated() in real time
                .anyRequest().authenticated()

                //Login Form Details
                .and()
                .formLogin()
                .defaultSuccessUrl("/welcome", true)

                //Logout Form Details
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                //Exception Details
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .and()
                .csrf().disable();
        http.headers().frameOptions().sameOrigin();

        return http.build();

    }
}