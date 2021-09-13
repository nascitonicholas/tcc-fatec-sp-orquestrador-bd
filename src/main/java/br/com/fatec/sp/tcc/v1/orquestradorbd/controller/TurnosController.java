package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TurnosResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.TurnosFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
