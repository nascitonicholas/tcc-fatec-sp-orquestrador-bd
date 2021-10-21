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
public class VagasRequestCreate {

    @Valid
    @JsonProperty("lista_vagas")
    private List<RequestCreate> request;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestCreate{

        @NotBlank(message = "Titulo deve ser informado")
        @JsonProperty("titulo")
        private String titulo;

        @NotBlank(message = "Data de Vencimento deve ser informada")
        @JsonProperty("data_vencimento")
        private String dataVencimento;

        @NotNull(message = "Número de vagas deve ser informado")
        @JsonProperty("numero_vagas")
        private Integer numeroVagas;

        @NotBlank(message = "Horário deve ser informado")
        @JsonProperty("horario")
        private String horario;

        @NotBlank(message = "Remuneração deve ser informado")
        @JsonProperty("remuneracao")
        private String remuneracao;

        @NotBlank(message = "Atividades devem ser informadas")
        @JsonProperty("atividades")
        private String atividades;

        @NotBlank(message = "Período da faculdade deve ser informado")
        @JsonProperty("periodo_faculdade")
        private String periodoFaculdade;

        @NotBlank(message = "Conhecimentos em informática deve ser informado")
        @JsonProperty("conhecimentos_informatica")
        private String conhecimentosInformatica;

        @NotBlank(message = "Conhecimento de línguas deve ser informado")
        @JsonProperty("conhecimentos_linguas")
        private String conhecimentosLinguas;

        @JsonProperty("observacoes")
        private String observacoes;

        @NotNull(message = "Id do curso deve ser informado")
        @JsonProperty("id_curso")
        private Long  idCurso;

        @NotNull(message = "NrMatricula deve ser informado")
        @JsonProperty("nr_matricula_usuario")
        private Long  nrMatriculaUsuario;

        @NotNull(message = "Id do tipo da vaga deve ser informado")
        @JsonProperty("id_tipo_vaga")
        private Long  idTipoVaga;

        @NotNull(message = "Id do curso deve ser informado")
        @JsonProperty("id_endereco_vaga")
        private Long idEnderecoVaga;
    }
}
