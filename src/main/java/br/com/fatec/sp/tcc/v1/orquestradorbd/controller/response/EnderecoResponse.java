package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponse {

    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String municipio;
    private String estado;
    private String cep;
    private String dataCriacao;
    private String dataUltimaAlteracao;
}
