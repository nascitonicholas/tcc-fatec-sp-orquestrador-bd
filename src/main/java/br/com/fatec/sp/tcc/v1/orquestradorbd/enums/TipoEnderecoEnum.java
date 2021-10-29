package br.com.fatec.sp.tcc.v1.orquestradorbd.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Locale;

public enum TipoEnderecoEnum {

    Residencial("Residencial"),
    Comercial("Comercial"),
    Cobrança("Cobrança");

    @Setter
    @Getter
    private String name;

    TipoEnderecoEnum(String name) {
        this.name = name;
    }

    public static TipoEnderecoEnum valuesOfString(String nome) {
        return Arrays.stream(TipoEnderecoEnum.values())
                .filter( item -> item.getName().equals(nome))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de endereço inválido " + nome));
    }
}
