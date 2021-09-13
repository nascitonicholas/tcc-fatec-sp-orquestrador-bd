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
public class TurnoRequestUpdate {

    @Valid
    @JsonProperty("lista_turnos")
    private List<RequestUpdate> request;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestUpdate {

        @NotNull(message = "ID deve ser informado")
        @JsonProperty("id")
        private Long id;
        @NotBlank(message = "Nome deve ser informado")
        @JsonProperty("nome")
        private String nome;
    }
}
