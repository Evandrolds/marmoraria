package com.evandro.marmoraria.resources.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.InputMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Evandro
 */

/*
   esse anotation serve para dar a essa class 
   a capacidade de inteceptar uma açao lá no resources e tratar ela aqui 
*/

@ControllerAdvice   
public class ResourceExceptionHandler {
    @ExceptionHandler(EntityNotFoundExceptionMarmor.class)
    public ResponseEntity<StandardError> entityNotfoundException(EntityNotFoundExceptionMarmor ex,HttpServletRequest request){
        StandardError erro = new StandardError();
        erro.setTimeStemp(Instant.now());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setMessage(ex.getMessage());
        erro.setPath(request.getRequestURI());
        erro.setError("Resource not found!");
        
       
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    @ExceptionHandler(InputMismatchException.class)
    public ResponseEntity<StandardError> inputMismatchException(InputMismatchExceptionsMarmor ex,HttpServletRequest request){
        StandardError erro = new StandardError();
        erro.setTimeStemp(Instant.now());
        erro.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        erro.setMessage(ex.getMessage());
        erro.setPath(request.getRequestURI());
        erro.setError(" Illegal argument!");
        
       
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(erro);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException ex,HttpServletRequest request){
        StandardError erro = new StandardError();
        erro.setTimeStemp(Instant.now());
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setMessage(ex.getMessage());
        erro.setPath( request.getRequestURI());
        erro.setError("You must enter only numbers");
        
       
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
    
}  
