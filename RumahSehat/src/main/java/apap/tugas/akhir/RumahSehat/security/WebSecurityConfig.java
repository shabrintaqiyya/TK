package apap.tugas.akhir.RumahSehat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/login-sso", "/validate-ticket").permitAll()
                .antMatchers("/user/viewall-dokter").hasAnyAuthority("Admin") 
                .antMatchers("/user/viewall-apoteker").hasAnyAuthority("Admin") 
                .antMatchers("/user/add-dokter").hasAnyAuthority("Admin") 
                .antMatchers("/user/add-apoteker").hasAnyAuthority("Admin") 
                .antMatchers("/user/appointment").hasAnyAuthority("Admin", "Dokter", "Pasien") 
                // .antMatchers("/penyelenggara/add").hasAnyAuthority("Manajer")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();
        return http.build();
    }

    // @Bean
    // public BCryptPasswordEncoder encoder() {
    //     return new BCryptPasswordEncoder();
    // }

    public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.inMemoryAuthentication()
    //             .passwordEncoder(encoder())
    //             .withUser("shabrina")
    //             .password(encoder().encode("apapABC"))
    //             .roles("USER");
    // }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
}