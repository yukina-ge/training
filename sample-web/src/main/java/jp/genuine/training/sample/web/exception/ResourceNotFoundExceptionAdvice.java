package jp.genuine.training.sample.web.exception;

import jp.genuine.training.sample.exception.ResourceNotFoundException;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceNotFoundExceptionAdvice 
{
	@ExceptionHandler( value = ResourceNotFoundException.class )
	public void resourceNotFoundException( HttpServletResponse response ) throws IOException
	{
		response.sendError( HttpStatus.NOT_FOUND.value());
	}
}
