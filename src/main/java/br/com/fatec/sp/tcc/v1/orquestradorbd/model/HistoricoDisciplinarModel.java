package br.com.fatec.sp.tcc.v1.orquestradorbd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "historico_disciplinar")
public class HistoricoDisciplinarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_disciplinar")
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_aluno")
    private UsuariosModel aluno;
    @OneToOne
    @JoinColumn(name = "id_materia")
    private MateriasModel idMateria;
    @Column(name = "ano_mes_conclusao")
    private String anoMesConclusao;
    @Column(name = "faltas")
    private String faltas;
    @Column(name = "nota_final")
    private String notaFinal;
    @Column(name = "conceito")
    private String conceito;
    @Column(name = "dt_criacao")
    private String dataCriacao;
    @Column(name = "dt_ultima_alteracao")
    private String dataUltimaAlteracao;

}
