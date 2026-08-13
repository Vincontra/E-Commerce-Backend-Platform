package com.ecom.project.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
//this means that Whenever validation fails anywhere in my application, handle that error here and give the client a clean response
// har ek controller se anewale exceptions will be handled by @RestControllerAdvice

public class MyGlobalExceptionHandler  {  // ok basically this is not a validation class we are doing so using @Valid;; this class just collect the errors,format the error properly
    // and returns them
    //@ExceptionHandler(Exception.class) this can also be written over here
    // but to be more specific i wrote the below one
    @ExceptionHandler(MethodArgumentNotValidException.class)  //If MethodArgumentNotValidException occurs,call the method below
    public ResponseEntity<Map<String,String>>myMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String>response=new HashMap<>();
         e.getBindingResult().getAllErrors().forEach(err ->{
             String fieldName=((FieldError)err).getField();  // kis field ki vajah se error occur hua that will be stored in fieldName
             String message=err.getDefaultMessage();  // and uska msg
             response.put(fieldName,message);
         });
        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String>myResourceNotFoundException(ResourceNotFoundException e){
        String message=e.getMessage();
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
}
