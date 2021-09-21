package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoricoDisciplinarRequestDelete {

    @Valid
    @JsonProperty("lista_historico_disciplinar")
    private List<HistoricoDisciplinarDelete> request;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class HistoricoDisciplinarDelete{

        @JsonProperty("id_historico_disciplinar")
        private Long id;
    }
}
