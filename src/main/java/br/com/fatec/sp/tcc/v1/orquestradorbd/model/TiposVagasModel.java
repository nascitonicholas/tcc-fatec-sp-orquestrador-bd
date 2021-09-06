package br.com.fatec.sp.tcc.v1.orquestradorbd.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tipo_vagas")
public class TiposVagasModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "tipo")
	private String tipo;
	@Column(name = "dt_criacao")
	private String dataCriacao;
	@Column(name = "dt_ultima_alteracao")
	private String dataUltimaAlteracao;

}
