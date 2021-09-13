package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.CursoResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.CursosFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum.*;

@RestController
@RequestMapping("/cursos")
public class CursosController implements AbstractController<SaidaDefault> {

    @Autowired
    private CursosFacade cursosFacade;


    @GetMapping
    public ResponseEntity<?> getCursos(){

        List<CursoResponse> cursos = cursosFacade.getCursos();

        return saidaSimplificada(SaidaDefault.builder().responseBody(cursos).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);

    };

    @PostMapping
    public ResponseEntity<?> postCursos(@RequestBody @Validated CursosRequestCreate cursosRequestCreates){

        cursosFacade.postCursos(cursosRequestCreates);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_CRIACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCursoById(@PathVariable Long id){

        CursoResponse curso = cursosFacade.getCursoById(id);

        return saidaSimplificada(SaidaDefault.builder().responseBody(curso).message(MESSAGE_SUCESSO_ID.getMessage()).build(), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<?> putCursos(@RequestBody @Validated CursosRequestUpdate cursosRequestUpdate){

        cursosFacade.putCursos(cursosRequestUpdate);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_ATUALIZACAO.getMessage()).build(), HttpStatus.OK);
    }


}
