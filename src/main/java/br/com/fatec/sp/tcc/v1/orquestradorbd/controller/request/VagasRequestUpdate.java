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
public class VagasRequestUpdate {

    @Valid
    @JsonProperty("lista_vagas")
    private List<RequestUpdate> request;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestUpdate{


        @NotNull(message = "Id da vaga deve ser informado")
        @JsonProperty("id_vaga")
        private Long id;

        @JsonProperty("titulo")
        private String titulo;

        @JsonProperty("data_vencimento")
        private String dataVencimento;

        @JsonProperty("numero_vagas")
        private Integer numeroVagas;

        @JsonProperty("horario")
        private String horario;

        @JsonProperty("remuneracao")
        private String remuneracao;

        @JsonProperty("atividades")
        private String atividades;

        @JsonProperty("periodo_faculdade")
        private String periodoFaculdade;

        @JsonProperty("conhecimentos_informatica")
        private String conhecimentosInformatica;

        @JsonProperty("conhecimentos_linguas")
        private String conhecimentosLinguas;

        @JsonProperty("observacoes")
        private String observacoes;

        @JsonProperty("id_curso")
        private Long  idCurso;

        @JsonProperty("id_tipo_vaga")
        private Long  idTipoVaga;

        @JsonProperty("id_endereco_vaga")
        private Long idEnderecoVaga;
    }
}
