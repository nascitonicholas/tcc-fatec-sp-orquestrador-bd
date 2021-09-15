package br.com.fatec.sp.tcc.v1.orquestradorbd.enums;

import lombok.Getter;

public enum MensagensErrorEnum {

    MESSAGE_ERROR_CREATE("Não foi possível salvar o recurso - Erro : "),
    MESSAGE_ERROR_DELETE("Não foi possível deletar o recurso - Erro : "),
    MESSAGE_ERROR_UPDATE("Não foi possível atualizar o recurso - Erro : "),
    MESSAGE_ERROR_FIND("Não foi possível encontrar o recurso - Erro : "),
    MESSAGE_ERROR_FOREING_KEY("Foi informado um valor inválido para o campo %s ");

    @Getter
    private String message;


    public String messageErroFk(String campo ){
        return String.format(MESSAGE_ERROR_FOREING_KEY.message,campo);
    }

    MensagensErrorEnum(final String message) { this.message = message;}
}
