package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VagasResponse {

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

    @JsonProperty("curso")
    private CursoResponse curso;

    @JsonProperty("tipo_vaga")
    private TiposVagasResponse  tipoVaga;

    @JsonProperty("endereco")
    private EnderecoResponse enderecoVaga;
}
