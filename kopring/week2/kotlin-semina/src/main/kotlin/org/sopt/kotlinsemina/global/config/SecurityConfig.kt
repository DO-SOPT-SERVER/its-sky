package org.sopt.kotlinsemina.global.config

import org.sopt.kotlinsemina.global.auth.filter.JwtAuthenticationFilter
import org.sopt.kotlinsemina.global.auth.filter.SoptExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val soptExceptionHandler: SoptExceptionHandler,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling()
            .authenticationEntryPoint(soptExceptionHandler)
            .accessDeniedHandler(soptExceptionHandler)
            .and()
            .authorizeHttpRequests()
            .requestMatchers(*AUTH_WHITELIST.toTypedArray()).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedOriginPatterns("*")
                    .allowedMethods("*")
            }
        }
    }

    companion object {
        private val AUTH_WHITELIST = listOf("/sign-up", "/sign-in")
    }
}