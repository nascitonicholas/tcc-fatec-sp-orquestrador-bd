package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MateriasResponse {

    @JsonProperty("id_materia")
    private Long id;
    @JsonProperty("cod_materia")
    private String codMateria;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("semestre")
    private String semestre;
    @JsonProperty("professores")
    private List<UsuarioResponseSimples> professores;
    @JsonProperty("data_criacao")
    private String dataCriacao;
    @JsonProperty("data_ultima_alteracao")
    private String dataUltimaAlteracao;
}
