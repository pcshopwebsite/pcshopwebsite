// package org.stevenguyendev.pcshopwebsite.configuration;

// import java.util.Arrays;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.cors.CorsConfiguration;

// @Configuration
// public class SecurityConfiguration {

//     @Bean
// 	SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
// 		http.authorizeHttpRequests().anyRequest().permitAll();
// 		return http.build();
// 	}

//     @Bean
//     public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        
//         CorsConfiguration corsConfiguration = new CorsConfiguration();
//         corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
//         corsConfiguration.setAllowedMethods(Arrays.asList("*"));
//         corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
//         http.cors().configurationSource(request -> corsConfiguration);
//         return http.build();
//     }
    
// }
