package jp.genuine.training.sample.web.healthcheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/healthcheck" )
public class HealthCheckController
{
	@RequestMapping( "" )
	public String check()
	{
		return "OK";
	}
}
