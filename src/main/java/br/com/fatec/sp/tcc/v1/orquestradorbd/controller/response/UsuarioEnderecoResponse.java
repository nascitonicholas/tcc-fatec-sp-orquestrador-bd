package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioEnderecoResponse {

    @JsonProperty("id_endereco")
    private Long id;
    @JsonProperty("logradouro")
    private String logradouro;
    @JsonProperty("numero")
    private String numero;
    @JsonProperty("complemento")
    private String complemento;
    @JsonProperty("bairro")
    private String bairro;
    @JsonProperty("municipio")
    private String municipio;
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("cep")
    private String cep;
    @JsonProperty("data_criacao")
    private String dataCriacao;
    @JsonProperty("data_ultima_alteracao")
    private String dataUltimaAlteracao;
}
