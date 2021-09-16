package br.com.fatec.sp.tcc.v1.orquestradorbd.model;

import javax.persistence.*;
import java.util.List;

public class ProtocolosRematriculaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_protocolo_rematricula")
    private Long id;
    @Column(name = "cod_protocolo")
    private String codProtrocolo;
    @OneToOne
    @JoinColumn(name = "id_aluno")
    private UsuariosModel aluno;
    @Column(name = "ano_semestre_rematricula")
    private String anoSemestreRematricula;
    @OneToMany()
    @JoinColumn(name = "id_turno")
    private List<TurnosModel> turno;
    @Column(name = "dt_criacao")
    private String dataCriacao;
    @Column(name = "dt_ultima_alteracao")
    private String dataUltimaAlteracao;

}
