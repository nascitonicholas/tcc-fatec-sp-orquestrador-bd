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
public class ProtocolosRematriculaRequestCreate {

    @Valid
    @JsonProperty("lista_protocolo_rematricula")
    private List<RequestCreate> request;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class RequestCreate{

        @NotNull(message = "Id da materia deve ser informado")
        @JsonProperty("ids_materia")
        private List<Long> idsMateria;

        @NotNull(message = "Id do aluno deve ser informado")
        @JsonProperty("nr_matricula_usuario")
        private Long nrMatriculaUsuario;

        @NotBlank(message = "Ano/Semestre da Rematricula deve ser informado")
        @JsonProperty("ano_semestre_rematricula")
        private String anoSemestreRematricula;

    }
}
