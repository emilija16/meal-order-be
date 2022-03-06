package com.example.demo.securitytoken.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${spring.security.secret-key}")
	private String secretKey;

	@Override  //konfigurisem i dozvoljavam kors, zabranio csrf i koristim secretKey, dobijam token i definisao sam entpointe  a ostale zahtevaju autentifikaciju
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
	      .sessionManagement()
	      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	      .and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/auth/**").permitAll()
		.antMatchers(HttpMethod.POST, "/api/v1/ordermeal/weeklyMenu").permitAll()
		.antMatchers(HttpMethod.GET, "/dailymenu").permitAll()
		//.antMatchers(HttpMethod.GET,"/mealtype").permitAll()
		.anyRequest().authenticated().and()
		//filter koji ce presretati svaki zahtev i proveravati token(ulogu)
		.addFilterBefore(new JWTAuthorizationFilter(secretKey), BasicAuthenticationFilter.class);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "X-Auth-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		configuration.setExposedHeaders(Arrays.asList("Content-Type", "X-Auth-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	
	}

	// ovde stavljam koje putanje filter preskace
	@Override
	public void configure(WebSecurity web) throws Exception {
		//web.ignoring().antMatchers(HttpMethod.POST, "/auth/**");

	}

}
