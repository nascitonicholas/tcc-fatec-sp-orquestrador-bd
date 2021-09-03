package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoResponse {

    private Long id;
    private String nome;
    private Date dataCriacao;
    private Date dataUltimaAlteracao;
}
