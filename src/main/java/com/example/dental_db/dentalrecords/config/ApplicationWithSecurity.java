package com.example.dental_db.dentalrecords.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ApplicationWithSecurity {
    //Create User
    @Autowired
    private UserDetailsService userDetailsService;

    //Password Encoder
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    //Chosen Provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder());
        return provider;
    }

    //HTTP Request API security
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //allow any mapping aside from get
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/patients/**").hasAuthority("ADMIN")
                .and().httpBasic().and().formLogin();
        return http.build();
    }


}
