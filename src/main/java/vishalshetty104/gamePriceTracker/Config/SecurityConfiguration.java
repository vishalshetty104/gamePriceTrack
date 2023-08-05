package vishalshetty104.gamePriceTracker.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import vishalshetty104.gamePriceTracker.Service.UserService;

@Component
@EnableWebSecurity
public class SecurityConfiguration{
    @Autowired
    UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http.authorizeHttpRequests(auth->auth
                .requestMatchers("/register").permitAll()
                .anyRequest().authenticated())
                .userDetailsService(userService)
                .formLogin(form->form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/").permitAll())
                .logout(Customizer.withDefaults())
                .build();
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
