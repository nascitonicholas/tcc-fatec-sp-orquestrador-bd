package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.UsuariosModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProtocolosRematriculaRequestUpdate {

    @Valid
    @JsonProperty("lista_protocolo_rematricula")
    private List<RequestUpdateProtocolo> request;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class RequestUpdateProtocolo{

        @JsonProperty("id_protocolo_rematricula")
        private Long id;
        @JsonProperty("ids_materias")
        private List<Long> idMateria;
        @JsonProperty("id_aluno")
        private Long idAluno;
        @JsonProperty("ano_semestre_rematricula")
        private String anoSemestreRematricula;
        @JsonProperty("id_turno")
        private List<Long> turno;
    }
}
