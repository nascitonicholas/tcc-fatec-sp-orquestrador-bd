package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursosRequestCreate {

    @Valid
    @JsonProperty("lista_cursos")
    private List<RequestCreate> request;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestCreate{

        @NotBlank(message = "Nome deve ser informado")
        @JsonProperty("nome")
        private String nome;
    }


}
