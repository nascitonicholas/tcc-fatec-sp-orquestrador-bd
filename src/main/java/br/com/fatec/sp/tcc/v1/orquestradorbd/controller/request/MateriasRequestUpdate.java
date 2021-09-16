package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriasRequestUpdate {

    @Valid
    @JsonProperty("lista_materias")
    private List<MateriasUpdate> request;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MateriasUpdate{

        @NotNull(message = "Id da materia deve ser informado")
        @JsonProperty("id_materia")
        private Long id;
        @JsonProperty("cod_materia")
        private String codMateria;
        @JsonProperty("nome")
        private String nome;
        @JsonProperty("semestre")
        private String semestre;
        @JsonProperty("ids_professores")
        private List<Long> idProfessores;

    }
}
