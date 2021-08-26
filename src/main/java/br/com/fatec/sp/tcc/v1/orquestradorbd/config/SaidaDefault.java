package br.com.fatec.sp.tcc.v1.orquestradorbd.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaidaDefault {

	@JsonProperty("data")
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private Object responseBody;
	
	@JsonProperty("message")
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String  message;
	
	@JsonProperty("description")
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String  description;

}
