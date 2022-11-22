package tspprocessproductservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tspprocessproductservice.dto.APIError;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({OfferNotFoundException.class})
    ResponseEntity<?> OfferNotFoundException(Exception exc, ServletWebRequest request){

        APIError apiError = new APIError();

        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setTimeStamp(LocalDateTime.now());
        apiError.setErrors(Arrays.asList(exc.getMessage()));
        apiError.setPathUri(request.getDescription(true));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {


        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<String> errors = fieldErrors.stream().map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage()).collect(Collectors.toList());

        APIError apiError = new APIError();
        
        apiError.setPathUri(request.getDescription(true));
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setErrors(errors);
        apiError.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
