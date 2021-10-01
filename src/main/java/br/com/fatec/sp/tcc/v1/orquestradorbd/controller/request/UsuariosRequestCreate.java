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
public class UsuariosRequestCreate {

    @Valid
    @JsonProperty("lista_usuarios")
    private List<RequestCreate> request;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestCreate{

        @NotNull(message = "Número da matrícula deve ser informado")
        @JsonProperty("nrMatricula")
        private Long nrMatricula;

        @NotBlank(message = "Nome deve ser informado")
        @JsonProperty("nome")
        private String nome;

        @NotBlank(message = "Email deve ser informado")
        @JsonProperty("email")
        private String email;

        @NotBlank(message = "Senha deve ser informada")
        @JsonProperty("senha")
        private String senha;

        @NotBlank(message = "CPF deve ser informado")
        @JsonProperty("cpf")
        private String cpf;

        @NotBlank(message = "RG deve ser informado")
        @JsonProperty("rg")
        private String rg;

        @NotBlank(message = "Certificado Militar deve ser informado")
        @JsonProperty("certificadoMilitar")
        private String certificadoMilitar;

        @NotBlank(message = "Número do Título deve ser informado")
        @JsonProperty("numeroTitulo")
        private String numeroTitulo;

        @NotBlank(message = "Zona do Título deve ser informado")
        @JsonProperty("zonaTitulo")
        private String zonaTitulo;

        @NotBlank(message = "Telefone deve ser informado")
        @JsonProperty("telefone")
        private String telefone;

        @NotBlank(message = "Celular deve ser informado")
        @JsonProperty("celular")
        private String celular;

        @NotNull(message = "Id do endereço deve ser informado")
        @JsonProperty("id_endereco")
        private Long idEndereco;

        @NotNull(message = "Id do curso deve ser informado")
        @JsonProperty("id_curso")
        private Long Idcurso;

        @NotNull(message = "Id do turno deve ser informado")
        @JsonProperty("id_turno")
        private Long Idturno;
    }



}
