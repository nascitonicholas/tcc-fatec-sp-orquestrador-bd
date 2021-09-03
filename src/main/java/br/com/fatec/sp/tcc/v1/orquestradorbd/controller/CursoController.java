package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.CursoResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.CursoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController implements AbstractController<SaidaDefault> {

    @Autowired
    private CursoFacade cursoFacade;


    @GetMapping
    public ResponseEntity<?> getCursos(){

        List<CursoResponse> cursos = cursoFacade.getCursos();

        return saidaSimplificada(SaidaDefault.builder().responseBody(cursos).message("Lista Retornada com Sucesso").build(), HttpStatus.OK);

    };
}
