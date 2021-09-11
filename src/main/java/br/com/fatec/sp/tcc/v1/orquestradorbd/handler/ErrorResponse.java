package br.com.fatec.sp.tcc.v1.orquestradorbd.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private List<String> details;
}
