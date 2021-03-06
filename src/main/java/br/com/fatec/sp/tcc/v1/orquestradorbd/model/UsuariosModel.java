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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuarios")
public class UsuariosModel {

    @Id
    @Column(name = "nr_matricula")
    private Long nrMatricula;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "email_pessoal")
    private String emailPessoal;
    @Column(name = "senha")
    private String senha;
    @Column(name = "nome_mae")
    private String nomeMae;
    @Column(name = "nome_pai")
    private String nomePai;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "rg")
    private String rg;
    @Column(name = "certificado_militar")
    private String certificadoMilitar;
    @Column(name = "numero_titulo")
    private String numeroTitulo;
    @Column(name = "zona_titulo")
    private String zonaTitulo;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "celular")
    private String celular;
    @OneToMany()
    @JoinColumn(name = "id_endereco_usuario")
    private List<EnderecosModel> enderecos;
    @OneToOne
    @JoinColumn(name = "id_curso_usuario")
    private CursosModel curso;
    @OneToOne
    @JoinColumn(name = "id_turno_usuario")
    private TurnosModel turno;
    @ManyToMany
    @JoinTable(name = "materias_usuarios",
            joinColumns = { @JoinColumn(name = "usuarios_nrMatricula") },
            inverseJoinColumns = { @JoinColumn(name = "materia_id") })
    private List<MateriasModel> materias = new ArrayList<>();
    @Column(name = "dt_criacao")
    private String dataCriacao;
    @Column(name = "dt_ultima_alteracao")
    private String dataUltimaAlteracao;


}
