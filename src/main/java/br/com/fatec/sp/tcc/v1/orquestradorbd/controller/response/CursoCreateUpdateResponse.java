package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoCreateUpdateResponse {

    @Valid
    @JsonProperty("id_curso")
    private Long id;
}
