package br.com.fatec.sp.tcc.v1.orquestradorbd.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

	private static final String formation = "yyyy/MM/dd HH:mm:ss";
	
	public static String uppercase(String value) {
		return value.toUpperCase();
	}

	public static String dataAtualFormatada(){
		LocalDateTime today = LocalDateTime .now();
		DateTimeFormatter formatter  = DateTimeFormatter.ofPattern(formation);
		return today.format(formatter);
	}

	public static String isNotNullOrEmpty(String entrada, String atual){

		if(entrada.isBlank()){
			return atual;
		}
		return  uppercase(entrada);
	}

	public static Integer numeroDivergente(Integer input, Integer atual){

		return input != atual ? input : atual;
	}

}
