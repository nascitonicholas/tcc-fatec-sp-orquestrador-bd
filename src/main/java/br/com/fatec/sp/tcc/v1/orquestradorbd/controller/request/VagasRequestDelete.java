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
public class VagasRequestDelete {

    @Valid
    @JsonProperty("lista_vagas")
    private List<RequestDelete> request;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestDelete{


        @NotNull(message = "Id da vaga deve ser informado")
        @JsonProperty("id_vaga")
        private Long id;

    }
}
