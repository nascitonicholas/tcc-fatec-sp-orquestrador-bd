package br.com.fatec.sp.tcc.v1.orquestradorbd.enums;

import lombok.Getter;

public enum MensagensEnum {

    MESSAGE_SUCESSO_LISTA("Lista retornada com sucesso."),
    MESSAGE_SUCESSO_ID("Busca por ID realizada com sucesso."),
    MESSAGE_SUCESSO_ATUALIZACAO("Atualizado com sucesso."),
    MESSAGE_SUCESSO_CRIACAO("Cadastrado com sucesso."),
    MESSAGE_SUCESSO_DELETADAS("Deletado com sucesso.");

    @Getter
    private String message;

    MensagensEnum(final String message) { this.message = message; }
}
