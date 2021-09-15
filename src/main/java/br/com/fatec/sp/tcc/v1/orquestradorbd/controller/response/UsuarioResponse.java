package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.CursosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TurnosModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("email")
    private String email;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("rg")
    private String rg;
    @JsonProperty("certificadoMilitar")
    private String certificadoMilitar;
    @JsonProperty("numeroTitulo")
    private String numeroTitulo;
    @JsonProperty("zonaTitulo")
    private String zonaTitulo;
    @JsonProperty("telefone")
    private String telefone;
    @JsonProperty("celular")
    private String celular;
    @JsonProperty("enderecos")
    private List<UsuarioEnderecoResponse> enderecos;
    @JsonProperty("curso")
    private UsuarioCursoResponse curso;
    @JsonProperty("turno")
    private UsuarioTurnoResponse turno;
    @JsonProperty("data_criacao")
    private String dataCriacao;
    @JsonProperty("data_ultima_alteracao")
    private String dataUltimaAlteracao;
}
