package apap.tugas.akhir.RumahSehat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import apap.tugas.akhir.RumahSehat.security.WebSecurityConfig;

@SpringBootApplication
public class RumahSehatApplication {
	public static void main(String[] args) {
		SpringApplication.run(RumahSehatApplication.class, args);
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    // @Bean
    // public WebSecurityConfig webSecurityConfig() {
    //     return new WebSecurityConfig();
    // }
}
