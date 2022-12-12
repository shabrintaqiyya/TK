package apap.tugas.akhir.RumahSehat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
// @ComponentScan(basePackages = {"apap.tugas.akhir.RumahSehat"})

public class WebSecurityConfig {
    @Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
        
                .authorizeRequests()
                // .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // .authorizeRequests().antMatchers("/auth/**").permitAll()
                // .antMatchers(HttpMethod.GET, "/user/allusers").permitAll()

                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/api/v1/pasien/add").permitAll()
                .antMatchers("/api/v1/pasien/list-pasien").permitAll()
                .antMatchers("/api/v1/dokter/list-dokter").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/login-sso", "/validate-ticket").permitAll()
                .antMatchers("/user/viewall-dokter").hasAnyAuthority("Admin") 
                .antMatchers("/user/viewall-apoteker").hasAnyAuthority("Admin") 
                .antMatchers("/user/viewall-pasien").hasAnyAuthority("Admin") 
                .antMatchers("/user/add-dokter").hasAnyAuthority("Admin") 
                .antMatchers("/user/add-apoteker").hasAnyAuthority("Admin") 
                .antMatchers("/user/appointment").hasAnyAuthority("Admin", "Dokter", "Pasien") 
                .antMatchers("/api/v1/list-dokter").hasAuthority("Pasien")
                .antMatchers("/api/v1/resep/detail/{id}").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // .loginPage("/login")
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();

        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);					

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    // @Bean
	// @Override
	// public AuthenticationManager authenticationManagerBean() throws Exception {
	// 	return super.authenticationManagerBean();
	// }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }
	@Bean
	public JwtTokenFilter jwtTokenFilter(){
		return new JwtTokenFilter();
	}
	// @Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.cors().and().csrf().disable()
					.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
					.authorizeRequests().antMatchers("/auth/**").permitAll()
					.antMatchers(HttpMethod.GET, "/api/v1/pasien").permitAll()

                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/api/v1/pasien/add").permitAll()
                    .antMatchers("/api/v1/pasien/list-pasien").permitAll()
                    .antMatchers("/login-sso", "/validate-ticket").permitAll()
                    .antMatchers("/user/viewall-dokter").hasAnyAuthority("Admin") 
                    .antMatchers("/user/viewall-apoteker").hasAnyAuthority("Admin") 
                    .antMatchers("/user/viewall-pasien").hasAnyAuthority("Admin") 
                    .antMatchers("/user/add-dokter").hasAnyAuthority("Admin") 
                    .antMatchers("/user/add-apoteker").hasAnyAuthority("Admin") 
                    .antMatchers("/user/appointment").hasAnyAuthority("Admin", "Dokter", "Pasien") 
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").permitAll();

					// .anyRequest().authenticated();
		
		httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);					
	}
}