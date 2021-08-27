package br.com.fatec.sp.tcc.v1.orquestradorbd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers(HttpMethod.GET, "*").permitAll()
				.antMatchers(HttpMethod.POST, "*").permitAll()
				.antMatchers(HttpMethod.PUT, "*").permitAll()
				.antMatchers(HttpMethod.DELETE, "*")
				.permitAll()
				.anyRequest().permitAll()
				;

	}
}
