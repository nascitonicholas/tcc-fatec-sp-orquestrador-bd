package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.MateriasModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TurnosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.UsuariosModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProtocolosRematriculaResponse {

    @JsonProperty("id_protocolo_rematricula")
    private Long id;
    @JsonProperty("cod_protocolo")
    private String codProtrocolo;
    @JsonProperty("materias")
    private List<MateriasResponse> materias;
    @JsonProperty("aluno")
    private UsuarioResponseSimples aluno;
    @JsonProperty("ano_semestre_rematricula")
    private String anoSemestreRematricula;
    @JsonProperty("dt_criacao")
    private String dataCriacao;
    @JsonProperty("dt_ultima_alteracao")
    private String dataUltimaAlteracao;
}
