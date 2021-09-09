package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursoRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.CursoResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.CursosFacade;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.CursosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursosController implements AbstractController<SaidaDefault> {

    @Autowired
    private CursosFacade cursosFacade;


    @GetMapping
    public ResponseEntity<?> getCursos(){

        List<CursoResponse> cursos = cursosFacade.getCursos();

        return saidaSimplificada(SaidaDefault.builder().responseBody(cursos).message("Cursos Encontrados").build(), HttpStatus.OK);

    };

    @PostMapping
    public ResponseEntity<?> postCursos(@RequestBody List<CursosRequestCreate> cursosRequestCreates){

        cursosFacade.postCursos(cursosRequestCreates);

        return saidaVoid(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCursoById(@PathVariable Long id){

        Optional<CursosModel> response = cursosFacade.getCursoById(id);

        if(response.isPresent()){
            return saidaSimplificada(SaidaDefault.builder().responseBody(response).message("Curso Encontrado").build(), HttpStatus.OK);
        }
        else{
            return saidaVoid(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> putCursos(@RequestBody List<CursosRequestUpdate> cursosRequestUpdate){

        cursosFacade.putCursos(cursosRequestUpdate);

        return saidaVoid(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCursos(@RequestBody List<CursoRequestDelete> cursoRequestDelete){

        cursosFacade.deleteCursos(cursoRequestDelete);

        return saidaVoid(HttpStatus.OK);
    }


}
