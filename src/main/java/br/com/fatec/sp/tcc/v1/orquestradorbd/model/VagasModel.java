package br.com.fatec.sp.tcc.v1.orquestradorbd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "vagas")
public class VagasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaga")
    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "dt_vencimento")
    private String dataVencimento;
    @Column(name = "numero_vagas")
    private Integer numeroVagas;
    @Column(name = "horario")
    private String horario;
    @Column(name = "remuneracao")
    private String remuneracao;
    @Column(name = "atividades")
    private String atividades;
    @Column(name = "periodo_faculdade")
    private String periodoFaculdade;
    @Column(name = "conhecimentos_informatica")
    private String conhecimentosInformatica;
    @Column(name = "conhecimentos_linguas")
    private String conhecimentosLinguas;
    @Column(name = "observacoes")
    private String observacoes;
    @OneToOne
    @JoinColumn(name = "id_curso_vaga")
    private CursosModel curso;
    @OneToOne
    @JoinColumn(name = "id_tipo_vaga")
    private TiposVagasModel tipoVaga;
    @OneToOne
    @JoinColumn(name = "id_endereco_vaga")
    private EnderecosModel enderecoVaga;
    @Column(name = "dt_criacao")
    private String dataCriacao;
    @Column(name = "dt_ultima_alteracao")
    private String dataUltimaAlteracao;

}
