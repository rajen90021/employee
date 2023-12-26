package Employe_wise.com.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import Employe_wise.com.dto.apiResponse;




@RestControllerAdvice
public class GlobalExceptionhandler {

	
	@ExceptionHandler(resourcenotfoundException.class)
	public ResponseEntity<apiResponse>   resourcenotfoundexception(resourcenotfoundException exception){
		
		
		
		   apiResponse apiResponse = new apiResponse();
		     apiResponse.setMessage("id not found ");
		     apiResponse.setStatus(HttpStatus.NOT_FOUND);
		     apiResponse.setSucess(false);
		
		     
		     return new ResponseEntity<apiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		
		
	}
	
	
	@ExceptionHandler(filenotfoundexpection.class)
	public ResponseEntity<apiResponse>  filenotfoundexception(filenotfoundexpection exception){
		
		
		
		     apiResponse apiResponse = new apiResponse();
		     
		     apiResponse.setMessage(exception.getMessage());
		     apiResponse.setStatus(HttpStatus.NOT_FOUND);
		     apiResponse.setSucess(false);
		
		     
		     return new ResponseEntity<apiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		
		
	}
   
}
