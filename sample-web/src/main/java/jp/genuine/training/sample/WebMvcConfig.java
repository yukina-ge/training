package jp.genuine.training.sample;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class WebMvcConfig {

	@Bean
	public MvcConfig mvcConfig()
	{
		return new MvcConfig();
	}

	public static class MvcConfig extends WebMvcConfigurerAdapter
	{
		@Override
		public void addViewControllers( ViewControllerRegistry registry )
		{
			registry.addRedirectViewController("/", "/index");
		}
	}
	
	@Configuration
	@ConditionalOnClass( { SpringSecurityDialect.class } )
	protected static class ThymeleafSecurityDialectConfiguration {
		
		@Bean
		@ConditionalOnMissingBean
		public SpringSecurityDialect securityDialect(){
			return new SpringSecurityDialect();
		}
	}
	
}
