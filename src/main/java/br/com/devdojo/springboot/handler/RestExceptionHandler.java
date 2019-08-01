package br.com.devdojo.springboot.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.devdojo.springboot.error.ErrorDetails;
import br.com.devdojo.springboot.error.ResourceNotFoundDetails;
import br.com.devdojo.springboot.error.ResourceNotFoundException;
import br.com.devdojo.springboot.error.ValidationErrorDetails;

@ControllerAdvice //Permite usar a camada RestException Handler através das camadas do spring
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rfnException){
		ResourceNotFoundDetails rfnDetails =  ResourceNotFoundDetails.Builder
													.newBuilder()
													.timestamp(new Date().getTime())
													.status(HttpStatus.NOT_FOUND.value())
													.title("Resource not found")
													.details("Resource not found " + rfnException.getMessage())
													.developerMessage(rfnException.getClass().getName())
													.build();
		
		return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
	}
	
	//@ExceptionHandler(MethodArgumentNotValidException.class) // como extendeu a classe ResponseEntityExceptionHandler,
	public ResponseEntity<?> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException manvException) {
		
		List<FieldError> filedErrors = manvException.getBindingResult().getFieldErrors();
		
		String fields = filedErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String fieldMessage = filedErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
		
		ValidationErrorDetails rfnDetails =  ValidationErrorDetails.Builder
													.newBuilder()
													.timestamp(new Date().getTime())
													.status(HttpStatus.BAD_REQUEST.value())
													.title("Field Validation Error")
													.details("Field Validation Error")
													.developerMessage(manvException.getClass().getName())
													.field(fields)
													.fieldMessage(fieldMessage)
													.build();
		
		return new ResponseEntity<>(rfnDetails, HttpStatus.BAD_REQUEST);
	}
	
	/*@Override 
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ErrorDetails errorDetails = ErrorDetails.Builder
				.newBuilder()
				.timestamp(new Date().getTime())
				.status(HttpStatus.NOT_FOUND.value())
				.title("Resource not found")
				.details(ex.getMessage())
				.developerMessage(ex.getClass().getName())
				.build();

		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}*/
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetails errorDetails =  ErrorDetails.Builder
				.newBuilder()
				.timestamp(new Date().getTime())
				.status(status.value())
				.title("Exception Internal")
				.details(ex.getMessage())
				.developerMessage(ex.getClass().getName())
				.build();
		
		return new ResponseEntity<>(errorDetails, headers, status);
	}
	
}
