package com.example.travelshooting.config;

import com.example.travelshooting.config.filter.JwtAuthFilter;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.server.ResponseStatusException;

@Configuration
@EnableWebSecurity // SecurityFilterChain 빈 설정을 위해 필요.
@RequiredArgsConstructor
public class WebConfig {

  private final JwtAuthFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final AuthenticationEntryPoint authEntryPoint;
  private final AccessDeniedHandler accessDeniedHandler;

  private static final String[] WHITE_LIST = {"/users/signup",
                                              "/users/login",
                                              "/admins",
                                              "/products/{productId}/reservations/{reservationId}/payment/approve",
                                              "/attachments",
                                              "/posters", // 포스터 전체조회
                                              "/posters/{posterId}", // 포스터 단건 조회
                                              "/posters/{posterId}/comments", // 댓글 전체 조회
                                              "/locals", // 장소 검색
                                              "/restaurants/**"};

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth ->
            auth.requestMatchers(WHITE_LIST).permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR).permitAll()
                .requestMatchers("/admins/**").hasRole("ADMIN")
                .requestMatchers("/partners/**").hasRole("PARTNER")
                .anyRequest().authenticated()
        )
        // Spring Security 예외에 대한 처리를 핸들러에 위임.
        .exceptionHandling(handler -> handler
            .authenticationEntryPoint((request, response, authException) -> {
              throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다. 유효한 자격 증명을 제공하세요.");
            })
            .accessDeniedHandler((request, response, accessDeniedException) -> {
              throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 거부되었습니다. 필요한 권한이 부족합니다.");
            }))
        // JWT 기반 테스트를 위해 SecurityContext를 가져올 때 HttpSession을 사용하지 않도록 설정.
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)

        /*
         * Spring Security와 관련된 예외(AuthenticationException, AccessDeniedException)는
         * ExceptionTranslationFilter에서 처리가 된다.
         *
         * ExceptionTranslationFilter의 doFilter()는 이후의 필터 체인에서 예외가 발생하면 그 예외를 처리하도록 작성되어 있다.
         * request를 넘겨 JwtAuthFilter에서 발생한 예외를 처리시키기 위해 ExceptionTranslationFilter 다음에 수행하도록 순서를 설정.
         */
        // ExceptionTranslationFilter --> jwtAuthFilter
//        .addFilterAfter(jwtAuthFilter, BasicAuthenticationFilter.class);
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public RoleHierarchy roleHierarchy() {
    try {
      return RoleHierarchyImpl.fromHierarchy(
          // "ROLE_ADMIN > ROLE_PARTNER > ROLE_USER"
          """
              ROLE_ADMIN > ROLE_PARTNER
              ROLE_PARTNER > ROLE_USER
              """);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "RoleHierarchy 초기화에 실패하였습니다.", ex);
    }
  }
}
