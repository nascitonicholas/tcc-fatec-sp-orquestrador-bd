package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiposVagasResponse {
	
	private Long id;
	private String tipo;
	private String dataCriacao;
	private String dataUltimaAlteracao;

}
