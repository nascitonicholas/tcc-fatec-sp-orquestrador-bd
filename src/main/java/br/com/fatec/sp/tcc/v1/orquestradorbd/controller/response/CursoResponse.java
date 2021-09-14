package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("data_criacao")
    private String dataCriacao;
    @JsonProperty("data_ultima_alteracao")
    private String dataUltimaAlteracao;
}
