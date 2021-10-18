package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import br.com.fatec.sp.tcc.v1.orquestradorbd.enums.TipoEnderecoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("tipo_endereco")
    private TipoEnderecoEnum tipoEndereco;
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
    private EstadosResponse estadosResponse;
    @JsonProperty("cep")
    private String cep;
    @JsonProperty("data_criacao")
    private String dataCriacao;
    @JsonProperty("data_ultima_alteracao")
    private String dataUltimaAlteracao;
}
