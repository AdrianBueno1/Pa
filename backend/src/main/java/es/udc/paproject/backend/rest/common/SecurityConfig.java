package es.udc.paproject.backend.rest.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.addFilter(new JwtFilter(authenticationManager(), jwtGenerator))
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/users/signUp").permitAll()
			.antMatchers(HttpMethod.POST,"/users/login").permitAll()
			.antMatchers(HttpMethod.POST,"/users/loginFromServiceToken").permitAll()
			.antMatchers(HttpMethod.PUT, "/users/*").hasAnyRole("USER", "TICKET_SELLER")
			.antMatchers(HttpMethod.POST, "/users/*/changePassword").hasAnyRole("USER", "TICKET_SELLER")
			.antMatchers(HttpMethod.GET,"/showtimes/cities").permitAll()
			.antMatchers(HttpMethod.GET,"/showtimes/cinemas").permitAll()
			.antMatchers(HttpMethod.GET,"/showtimes/cinemas/*/movieShowtimes").permitAll()
			.antMatchers(HttpMethod.GET,"/showtimes/sessions/*").permitAll()
			.antMatchers(HttpMethod.GET,"/showtimes/movies/*").permitAll()
			.antMatchers(HttpMethod.POST, "/shopping/sales/*/deliverTickets").hasRole("TICKET_SELLER")
			.anyRequest().hasRole("USER");

	} 
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration config = new CorsConfiguration();
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		config.setAllowCredentials(true);
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    
	    source.registerCorsConfiguration("/**", config);
	    
	    return source;
	    
	 }

}
