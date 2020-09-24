package ir.seefa.tutorial.spring.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since Mon, 21 Sep 2020 17:46:57 IRST
 */
// 1. define custom exception with specific Http error code
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message){
    	super(message);
    }
}
