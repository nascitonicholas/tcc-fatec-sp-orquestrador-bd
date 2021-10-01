package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request;

import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestFind {

    @NotBlank(message = "Número de Matricula deve ser informado")
    @JsonProperty("nrMatricula")
    private String nrMatricula;
    @JsonProperty("senha")
    private String senha;


    public String getSenhaEncriptada(){
        return this.senha = Utils.encodeSenha(this.senha);
    }


}
