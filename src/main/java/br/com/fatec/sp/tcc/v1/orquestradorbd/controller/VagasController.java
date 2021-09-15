package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.VagasRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.VagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.VagasFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum.MESSAGE_SUCESSO_DELETADAS;
import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum.MESSAGE_SUCESSO_LISTA;

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

    @DeleteMapping
    public ResponseEntity<?> deleteCursos(@RequestBody @Validated VagasRequestDelete vagasRequestDelete){

        vagasFacade.deleteVagas(vagasRequestDelete);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_DELETADAS.getMessage()).build(), HttpStatus.OK);
    }
}
