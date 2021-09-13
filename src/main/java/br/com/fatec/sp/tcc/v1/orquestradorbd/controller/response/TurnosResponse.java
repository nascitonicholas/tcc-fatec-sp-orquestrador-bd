package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TurnosResponse {

    private Long id;
    private String nome;
    private String dataCriacao;
    private String dataUltimaAlteracao;
}
