package com.example.Book_project.configuration;

import com.example.Book_project.filter.JwtTokenFilter;
import com.example.Book_project.model.User;
import com.example.Book_project.repository.UserRepository;
import com.example.Book_project.serviceimpl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userName -> {
            User user = userRepository.findByUserName(userName)
                    .orElseThrow(() -> new UsernameNotFoundException("userName not found with " + userName));
            UserDetails userDetails = UserDetailsImpl.build(user);
            return userDetails;
        }).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();
        http = http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
        http = http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, ex) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                })
                .and();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/saveOrUpdateUser").permitAll()
                //.antMatchers(HttpMethod.POST, "/api/saveOrUpdateBook").permitAll()
                .antMatchers(HttpMethod.POST, "/api/saveOrUpdateOrder").permitAll()
                //.antMatchers(HttpMethod.POST, "/api/saveOrUpdateReview").permitAll()
                .antMatchers("/api/login/**").permitAll()
//                .antMatchers("/api/admin/**").hasRole("ADMIN") // Example for role-based access
                .antMatchers("/api/saveOrUpdateBook").hasRole("Admin")
                .antMatchers("/api/findBookById").hasRole("Admin")
                .antMatchers("/api/deleteBookById").hasRole("Admin")
                .antMatchers("/api/saveOrUpdateReview").hasRole("Customer")
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override
    @Bean
    //here we override this method to expose 'Authrntication Manager' as spring bean
    //it is required to use 'AuthenticationManager' in other parts of this project like in 'AuthApi controller'
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//SecurityConfigurer class ends here
}
