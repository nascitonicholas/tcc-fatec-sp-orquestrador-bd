package br.com.fatec.sp.tcc.v1.orquestradorbd.utils;


import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuarioRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.UsuariosModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;

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
        LocalDateTime data = LocalDateTime.parse(dataString, formatterInput);
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


    public static String getStringAleatoria() {

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 35;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static String encodeSenha(String senha) {

        try {

            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");

            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            StringBuilder hexSenha = new StringBuilder();
            for (byte b : messageDigest) {
                hexSenha.append(String.format("%02X", 0xFF & b));
            }

            return hexSenha.toString();

        } catch (Exception e) {
            return null;
        }
    }

    public static String verificarSenha(UsuarioRequestUpdate.RequestUpdate requestUpdate, UsuariosModel usuariosModel) {


        if(Objects.isNull(requestUpdate.getSenhaAtual()) || Objects.isNull(requestUpdate.getNovaSenha())){
            return usuariosModel.getSenha();
        }
        else{
            String senhaAntiga = encodeSenha(requestUpdate.getSenhaAtual());

            if (senhaAntiga.equals(usuariosModel.getSenha())) {

                return encodeSenha(requestUpdate.getNovaSenha());
            } else {

                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "As senhas diferem");
            }
        }

    }

}
