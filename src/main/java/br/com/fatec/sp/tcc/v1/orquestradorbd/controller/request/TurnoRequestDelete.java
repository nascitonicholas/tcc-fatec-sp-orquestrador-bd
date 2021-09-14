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
public class TurnoRequestDelete {

    @Valid
    @JsonProperty("lista_turnos")
    private List<RequestDelete> requestDelete;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestDelete{

        @JsonProperty("id")
        @NotNull(message = "ID deve ser informado")
        private Long id;
    }
}
