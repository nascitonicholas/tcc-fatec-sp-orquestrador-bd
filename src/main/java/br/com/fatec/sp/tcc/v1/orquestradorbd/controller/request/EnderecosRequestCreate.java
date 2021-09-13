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
public class EnderecosRequestCreate {

    @Valid
    @JsonProperty("lista_enderecos")
    private List<RequestCreate> request;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestCreate{

        @NotBlank(message = "Logradouro deve ser informado")
        @JsonProperty("logradouro")
        private String logradouro;
        @NotBlank(message = "Numero deve ser informado")
        @JsonProperty("numero")
        private String numero;
        @NotBlank(message = "Complemento deve ser informado")
        @JsonProperty("complemento")
        private String complemento;
        @NotBlank(message = "Bairro deve ser informado")
        @JsonProperty("bairro")
        private String bairro;
        @NotBlank(message = "Municipio deve ser informado")
        @JsonProperty("municipio")
        private String municipio;
        @NotBlank(message = "Estado deve ser informado")
        @JsonProperty("estado")
        private String estado;
        @NotBlank(message = "CEP deve ser informado")
        @JsonProperty("cep")
        private String cep;
    }
}
