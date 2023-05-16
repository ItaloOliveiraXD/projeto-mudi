package br.com.projeto.mudi.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	UserDetailsManager users(DataSource dataSource) {
//		UserDetails user = User.builder()
//			.username("user")
//			.password(passwordEncoder().encode("user"))
//			.roles("ADM")
//			.build();
		
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//		users.createUser(user);
		
		return users;
	}
    
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/home/**").permitAll()
                .antMatchers("/usuario/novoCadastro").permitAll()
                .antMatchers("/usuario/cadastraUsuario").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin((form) -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/usuario/pedidos", true)
                                .permitAll()
                )
                .logout((logout) -> {
                    logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/home");
                })
                .csrf().disable();

		return http.build();
	}
}
