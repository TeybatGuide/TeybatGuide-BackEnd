package toyproject.genshin.teybatguide.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CorsFilter;
import toyproject.genshin.teybatguide.domain.value.Role;
import toyproject.genshin.teybatguide.jwt.CustomAuthenticationEntryPoint;
import toyproject.genshin.teybatguide.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilter(corsFilter);

        http
                .authorizeRequests((authz) -> authz
                                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                                .requestMatchers("/bookmark/**", "/user/mypage/**").hasRole(Role.USER.name())
                                .requestMatchers("/admin/**").hasRole(Role.ADMIN.name())
                                .anyRequest().authenticated()
                )
                .exceptionHandling((exceptionConfig) ->
                        exceptionConfig.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                );

        http
                .addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
