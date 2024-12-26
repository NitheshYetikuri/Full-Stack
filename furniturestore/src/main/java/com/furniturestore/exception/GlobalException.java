package com.furniturestore.exception;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
 
@RestControllerAdvice
public class GlobalException  {

	 @ExceptionHandler(RegistrationException.class)
	    public ResponseEntity<String> handleCarNotFoundException(RegistrationException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }

		@ExceptionHandler(UserNotFoundException.class)
		public ResponseEntity<String> handlerUserNotFoundException(UserNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
 
		@ExceptionHandler(ProductNotFoundException.class)
		public ResponseEntity<String> handlerBookingNotFoundException(ProductNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		 @ExceptionHandler(RoleCreationException.class)
		    public ResponseEntity<String> handlerUserAlreadyExistsException(RoleCreationException ex) {
		        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		    }
		
		 @ExceptionHandler(ProductAdditionException.class)
		    public ResponseEntity<String> handlerUserAlreadyExistsException(ProductAdditionException ex) {
		        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		    }
		 
		 @ExceptionHandler(InvalidCredentialsException.class)
		    public ResponseEntity<String> handlerUserAlreadyExistsException(InvalidCredentialsException ex) {
		        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		    }
 
}
