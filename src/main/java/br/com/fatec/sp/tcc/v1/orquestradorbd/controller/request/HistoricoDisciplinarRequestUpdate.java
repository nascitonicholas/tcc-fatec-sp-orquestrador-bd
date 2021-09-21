package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoricoDisciplinarRequestUpdate {


    @Valid
    @JsonProperty("lista_historico_disciplinar")
    private List<HistoricoDisciplinarUpdate> request;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class HistoricoDisciplinarUpdate{

        @NotNull(message = "Id do histórico deve ser informado")
        @JsonProperty("id_historico_disciplinar")
        private Long idHistoricoDisciplinar;
        @JsonProperty("ano_mes_conclusao")
        private String anoMesConclusao;
        @JsonProperty("faltas")
        private String faltas;
        @JsonProperty("nota_final")
        private String notaFinal;
        @JsonProperty("conceito")
        private String conceito;

    }


}
