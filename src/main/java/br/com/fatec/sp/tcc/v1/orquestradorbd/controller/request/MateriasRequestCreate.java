package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriasRequestCreate {

    @Valid
    @JsonProperty("lista_materias")
    private List<MateriaCreate> request;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MateriaCreate {

        @NotBlank(message = "Código da materia deve ser informado")
        @JsonProperty("cod_materia")
        private String codMateria;
        @NotBlank(message = "Nome da materia deve ser informado")
        @JsonProperty("nome")
        private String nome;
        @NotBlank(message = "Semestre da materia deve ser informado")
        @JsonProperty("semestre")
        private String semestre;
        @NotNull(message = "Deve ser informado pelo menos 1 profesor")
        @JsonProperty("ids_professores")
        private List<Long> idProfessores;

    }
}
