package br.com.fatec.sp.tcc.v1.orquestradorbd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuarios")
public class UsuariosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
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
    @Column(name = "dt_criacao")
    private String dataCriacao;
    @Column(name = "dt_ultima_alteracao")
    private String dataUltimaAlteracao;


}
