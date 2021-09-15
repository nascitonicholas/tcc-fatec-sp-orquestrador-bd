package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiposVagasResponse {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("tipo")
	private String tipo;
	@JsonProperty("data_criacao")
	private String dataCriacao;
	@JsonProperty("data_ultima_alteracao")
	private String dataUltimaAlteracao;

}
