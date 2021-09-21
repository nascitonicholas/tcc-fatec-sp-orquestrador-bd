package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.VagasRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.VagasRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.VagasRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.VagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.VagasFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum.*;

@RestController
@RequestMapping("/vagas")
public class VagasController implements AbstractController<SaidaDefault> {

    @Autowired
    private VagasFacade vagasFacade;

    @GetMapping
    public ResponseEntity<?> getVagas(){

        List<VagasResponse> vagas = vagasFacade.getVagas();

        return saidaSimplificada(SaidaDefault.builder().responseBody(vagas).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVagasById(@PathVariable Long id){

        VagasResponse vaga = vagasFacade.getById(id);

        return saidaSimplificada(SaidaDefault.builder().responseBody(vaga).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);

    }

    @GetMapping("/cursos/{idCurso}")
    public ResponseEntity<?> getVagasByCurso(@PathVariable Long idCurso){

        List<VagasResponse> vagasByCurso = vagasFacade.getVagasByCurso(idCurso);

        return saidaSimplificada(SaidaDefault.builder().responseBody(vagasByCurso).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postVagas(@RequestBody @Validated VagasRequestCreate vagasRequestCreate){

        vagasFacade.postVagas(vagasRequestCreate);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_CRIACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> putVagas(@RequestBody @Validated VagasRequestUpdate vagasRequestUpdate){

        vagasFacade.putVagas(vagasRequestUpdate);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_ATUALIZACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteVagas(@RequestBody @Validated VagasRequestDelete vagasRequestDelete){

        vagasFacade.deleteVagas(vagasRequestDelete);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_DELETADAS.getMessage()).build(), HttpStatus.OK);
    }
}
