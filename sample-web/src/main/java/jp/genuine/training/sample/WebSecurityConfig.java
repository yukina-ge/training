package jp.genuine.training.sample;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(
					"/webjars/**",
					"/images/**",
					"/css/**",
					"/js/**",
					"/errors/**",
					"/login",
					"/logout",
					"/healthcheck").permitAll()
			.antMatchers("/index/**").hasAnyRole("USER","ADMIN");
		
		http.formLogin()
        	.defaultSuccessUrl("/index");
		
		http.logout()
    		.logoutRequestMatcher( new AntPathRequestMatcher( "/logout" ) );
		
		http.csrf();
	}

    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("user1").password("password1").roles("USER");
            auth.inMemoryAuthentication().withUser("user2").password("password2").roles("ADMIN");
        }
    }
}
