package br.com.fatec.sp.tcc.v1.orquestradorbd.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDetails {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}
