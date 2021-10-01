package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioRequestDelete {

    @Valid
    @JsonProperty("lista_usuarios")
    private List<RequestDelete> request;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class RequestDelete{

        @NotNull(message = "Número da matrícula deve ser informado")
        @JsonProperty("nrMatricula")
        private Long nrMatricula;
    }
}
