package com.example.beststore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Configuration
public class SpringSecurity {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // Các trang công khai không yêu cầu đăng nhập
                        .requestMatchers(
                                "/register/**", "/home", "/css/**", "/js/**", "/images/**",
                                "/php/**", "/webfonts/**", "/product_page", "/detail/**",
                                "/product_page/search", "/about/**", "/my_account/**", "/api/**"
                        ).permitAll()

                        // Chỉ ADMIN mới được vào các trang admin
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Yêu cầu quyền ADMIN cho quản lý người dùng
                        .requestMatchers("/users").hasRole("ADMIN")

                        // Tất cả các request khác yêu cầu xác thực
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login") // Trang đăng nhập tùy chỉnh
                        .successHandler(customAuthenticationSuccessHandler()) // Bộ xử lý đăng nhập thành công
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout") // URL để thực hiện đăng xuất
                        .logoutSuccessUrl("/login") // Chuyển hướng sau khi đăng xuất
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
                // Lấy vai trò của người dùng
                boolean isAdmin = authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

                if (isAdmin) {
                    response.sendRedirect("/admin/product_page"); // Chuyển hướng ADMIN
                } else {
                    response.sendRedirect("/"); // Chuyển hướng người dùng thường
                }
            }
        };
    }
}
