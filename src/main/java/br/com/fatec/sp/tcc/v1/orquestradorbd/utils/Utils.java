package br.com.fatec.sp.tcc.v1.orquestradorbd.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {


    private static final String formation = "yyyy/MM/dd HH:mm:ss";
	private static final String formationInputData = "yyyy-MM-dd HH:mm:ss";
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formation);
	private static DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern(formationInputData);

    public static String uppercase(String value) {
        return value.toUpperCase();
    }

    public static String dataAtualFormatada() {
        LocalDateTime today = LocalDateTime.now();
        return today.format(formatter);
    }

    public static String fortacaoData(String dataString) {
        LocalDateTime data = LocalDateTime.parse(dataString,formatterInput);
		return data.format(formatter);
    }

    public static String isNotNullOrEmpty(String entrada, String atual) {

        if (entrada.isBlank()) {
            return atual;
        }
        return entrada;
    }

    public static Integer numeroDivergente(Integer input, Integer atual) {

        return input != atual ? input : atual;
    }


    public static void main(String[] args) {

    }

}
