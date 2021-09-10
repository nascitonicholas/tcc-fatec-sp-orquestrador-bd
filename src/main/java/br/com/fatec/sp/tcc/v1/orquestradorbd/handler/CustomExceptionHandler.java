package br.com.fatec.sp.tcc.v1.orquestradorbd.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<Object> handleAllException(ResponseStatusException e, WebRequest request){

        List<String> details = new ArrayList<>();
        details.add(e.getReason());

        ErrorResponse error = new ErrorResponse(details);

        return new ResponseEntity(error,e.getStatus());
    }
}
