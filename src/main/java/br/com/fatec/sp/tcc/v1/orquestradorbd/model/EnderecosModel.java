package br.com.fatec.sp.tcc.v1.orquestradorbd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "enderecos")
public class EnderecosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "numero")
    private String numero;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "municipio")
    private String municipio;
    @Column(name = "estado")
    private String estado;
    @Column(name = "cep")
    private String cep;
    //@ManyToOne
    //@JoinColumn(name = "id_endereco_usuario", referencedColumnName = "id_usuario")
    //private UsuariosModel usuario;
    @Column(name = "dt_criacao")
    private String dataCriacao;
    @Column(name = "dt_ultima_alteracao")
    private String dataUltimaAlteracao;
}
