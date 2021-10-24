package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.MateriasRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.MateriasRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.MateriasRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.MateriasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.MateriasFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum.*;

@RestController
@RequestMapping("/materias")
public class MateriasController implements AbstractController<SaidaDefault> {

    @Autowired
    private MateriasFacade materiasFacade;

    @GetMapping
    public ResponseEntity<?> getMaterias(){

        List<MateriasResponse> materias = materiasFacade.getMaterias();

        return saidaSimplificada(SaidaDefault.builder().responseBody(materias).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMateriasById(@PathVariable Long id){

        MateriasResponse materia = materiasFacade.getById(id);

        return saidaSimplificada(SaidaDefault.builder().responseBody(materia).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> postMaterias(@RequestBody @Validated MateriasRequestCreate materiasRequestCreate){

        materiasFacade.postMaterias(materiasRequestCreate);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_CRIACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> putMaterias(@RequestBody @Validated MateriasRequestUpdate materiasRequestUpdate){

        materiasFacade.putMaterias(materiasRequestUpdate);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_ATUALIZACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMaterias(@RequestBody @Validated MateriasRequestDelete materiasRequestDelete){

        materiasFacade.deleteMaterias(materiasRequestDelete);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_DELETADAS.getMessage()).build(), HttpStatus.OK);
    }


}
