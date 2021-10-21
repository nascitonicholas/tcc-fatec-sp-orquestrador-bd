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
public class HistoricoDisciplinarRequestCreate {


    @Valid
    @JsonProperty("lista_historico_disciplinar")
    private List<HistoricoDisciplinarCreate> request;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class HistoricoDisciplinarCreate{

        @NotNull(message = "Nr Matricula do aluno deve ser informado")
        @JsonProperty("nr_matricula_usuario")
        private Long nrMatriculaUsuario;
        @NotNull(message = "Id da materia deve ser informada")
        @JsonProperty("id_materia")
        private Long idMateria;
        @NotBlank(message = "Ano/Mês de conclusão devem ser informados")
        @JsonProperty("ano_mes_conclusao")
        private String anoMesConclusao;
        @NotBlank(message = "Número de faltas deve ser informado")
        @JsonProperty("faltas")
        private String faltas;
        @NotBlank(message = "Nota final deve ser informada")
        @JsonProperty("nota_final")
        private String notaFinal;
        @NotBlank(message = "Conceito deve ser informado")
        @JsonProperty("conceito")
        private String conceito;

    }


}
