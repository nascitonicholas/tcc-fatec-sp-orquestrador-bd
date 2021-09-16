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
public class MateriasRequestDelete {

    @Valid
    @JsonProperty("lista_materias")
    private List<MateriasDelete> request;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MateriasDelete{

        @NotNull(message = "Id da materia deve ser informado")
        @JsonProperty("id_materia")
        private Long id;
    }
}
