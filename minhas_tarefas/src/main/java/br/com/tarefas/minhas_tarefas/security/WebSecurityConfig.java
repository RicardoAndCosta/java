package br.com.tarefas.minhas_tarefas.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private static final String[] PATHS = new String[] {"/tarefa/**", "/categoria/**", "/usuario/**"};

    /**
     * Bean para codificador de senhas usando BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Bean para o AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configuração de autorização usando SecurityFilterChain.
     */
    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable().cors()  // Desativa CSRF (configurar conforme necessário)
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeHttpRequests()//auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // Permite acesso público a certas URLs
                .requestMatchers(HttpMethod.POST, PATHS)
                    .hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, PATHS)
                    .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, PATHS)
                    .hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, PATHS)
                    .hasAnyRole("ADMIN", "USER")
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated() // Exige autenticação para todas as outras
            .and()
                .httpBasic()
            .and()
                .formLogin(); // Configura login via formulário (ou personalize conforme necessário)
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("USER", "ADMIN")
                .build(),
            User.withUsername("user")
                .password(passwordEncoder().encode("user123"))
                .roles("USER")
                .build()
        );
    }
}