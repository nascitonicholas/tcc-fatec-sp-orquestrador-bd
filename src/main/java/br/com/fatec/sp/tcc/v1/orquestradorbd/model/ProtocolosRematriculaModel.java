package br.com.fatec.sp.tcc.v1.orquestradorbd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "protocolos_rematriculas")
public class ProtocolosRematriculaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_protocolo_rematricula")
    private Long id;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cod_protocolo")
    private String codProtrocolo;
    @OneToMany
    @JoinColumn(name = "ids_materia")
    private List<MateriasModel> materias;
    @OneToOne
    @JoinColumn(name = "id_aluno")
    private UsuariosModel aluno;
    @Column(name = "ano_semestre_rematricula")
    private String anoSemestreRematricula;
    @Column(name = "dt_criacao")
    private String dataCriacao;
    @Column(name = "dt_ultima_alteracao")
    private String dataUltimaAlteracao;

}
