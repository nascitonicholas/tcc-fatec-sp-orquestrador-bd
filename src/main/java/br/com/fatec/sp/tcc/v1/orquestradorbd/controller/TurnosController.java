package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TurnoRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TurnoRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TurnoRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TurnoCreateUpdateResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TurnosResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.TurnosFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum.*;

@RestController
@RequestMapping("/turnos")
public class TurnosController implements AbstractController<SaidaDefault> {

    @Autowired
    private TurnosFacade turnosFacade;

    @GetMapping
    public ResponseEntity<?> getTurnos(){

        List<TurnosResponse> turnos = turnosFacade.getTurnos();

        return saidaSimplificada(SaidaDefault.builder().responseBody(turnos).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTurnosById(@PathVariable Long id){

        TurnosResponse turno = turnosFacade.getTurnoById(id);

        return saidaSimplificada(SaidaDefault.builder().responseBody(turno).message(MESSAGE_SUCESSO_ID.getMessage()).build(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> postTurnos(@RequestBody @Validated TurnoRequestCreate turnoRequestCreate){

        List<TurnoCreateUpdateResponse> responses = turnosFacade.postTurnos(turnoRequestCreate);

        return saidaSimplificada(SaidaDefault.builder().responseBody(responses).message(MESSAGE_SUCESSO_CRIACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> putTurnos(@RequestBody @Validated TurnoRequestUpdate turnoRequestUpdate){

        List<TurnoCreateUpdateResponse> responses = turnosFacade.putTurnos(turnoRequestUpdate);

        return saidaSimplificada(SaidaDefault.builder().responseBody(responses).message(MESSAGE_SUCESSO_ATUALIZACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTurnos(@RequestBody @Validated TurnoRequestDelete turnoRequestDelete){

        turnosFacade.deleteTurnos(turnoRequestDelete);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_DELETADAS.getMessage()).build(), HttpStatus.OK);
    }
}
