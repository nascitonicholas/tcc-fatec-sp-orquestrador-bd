package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiposVagasRequest {

	@NotNull
	@JsonProperty("tipo")
	private String tipo;


}
