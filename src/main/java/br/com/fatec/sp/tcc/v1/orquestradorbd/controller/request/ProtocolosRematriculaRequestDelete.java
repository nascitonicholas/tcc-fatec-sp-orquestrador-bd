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
public class ProtocolosRematriculaRequestDelete {

    @Valid
    @JsonProperty("lista_protocolo_rematricula")
    private List<ProtocoloRematriculaRequestDelete> request;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ProtocoloRematriculaRequestDelete{

        @JsonProperty("id_protocolo_rematricula")
        private Long id;
    }
}
