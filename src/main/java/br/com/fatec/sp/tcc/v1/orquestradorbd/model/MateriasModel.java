package br.com.fatec.sp.tcc.v1.orquestradorbd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "materias")
public class MateriasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long id;
    @Column(name = "cod_materia")
    private String codMateria;
    @Column(name = "nome")
    private String nome;
    @Column(name = "semestre")
    private String semestre;
    @ManyToMany(targetEntity = UsuariosModel.class, cascade = CascadeType.ALL)
    private List<UsuariosModel> professores = new ArrayList<>();
    @Column(name = "dt_criacao")
    private String dataCriacao;
    @Column(name = "dt_ultima_alteracao")
    private String dataUltimaAlteracao;

}
