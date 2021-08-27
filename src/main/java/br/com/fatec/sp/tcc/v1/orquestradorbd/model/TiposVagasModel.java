package br.com.fatec.sp.tcc.v1.orquestradorbd.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	private Date dataCriacao;
	@Column(name = "dt_ultima_alteracao")
	private Date dataUltimaAlteracao;

}
