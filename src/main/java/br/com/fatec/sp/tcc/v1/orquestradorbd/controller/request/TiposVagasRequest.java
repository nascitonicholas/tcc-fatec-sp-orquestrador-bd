package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class TiposVagasRequest {
	
	@NotNull
	@JsonProperty("tipo")
	private String tipo;

}
