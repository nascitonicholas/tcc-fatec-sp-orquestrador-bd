package br.com.fatec.sp.tcc.v1.orquestradorbd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "turnos")
public class TurnosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "dt_criacao")
    private String dataCriacao;
    @Column(name = "dt_ultima_alteracao")
    private String dataUltimaAlteracao;

}
