package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MateriaResponseSimples {

    @JsonProperty("id_materia")
    private Long id;
    @JsonProperty("cod_materia")
    private String codMateria;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("semestre")
    private String semestre;
}
