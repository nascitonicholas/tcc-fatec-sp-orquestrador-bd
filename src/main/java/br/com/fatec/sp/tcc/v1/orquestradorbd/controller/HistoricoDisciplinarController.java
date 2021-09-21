package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.HistoricoDisciplinarRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.HistoricoDisciplinarRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.HistoricoDisciplinarRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuarioRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.HistoricoDisciplinarResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.HistoricoDisciplinarFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum.*;

@RestController
@RequestMapping("/historicoDisciplinar")
public class HistoricoDisciplinarController implements AbstractController<SaidaDefault> {


    @Autowired
    private HistoricoDisciplinarFacade historicoDisciplinarFacade;

    @GetMapping
    public ResponseEntity<?> getHistoricosDisciplinares(){

        List<HistoricoDisciplinarResponse> historicosDisciplinares = historicoDisciplinarFacade.getHistoricosDisciplinares();

        return saidaSimplificada(SaidaDefault.builder().responseBody(historicosDisciplinares).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<?> getHistoricosDisciplinaresByAluno(@PathVariable Long idAluno){

        List<HistoricoDisciplinarResponse> historicosDisciplinares = historicoDisciplinarFacade.getHistoricoDisciplinaresByAluno(idAluno);

        return saidaSimplificada(SaidaDefault.builder().responseBody(historicosDisciplinares).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postHistoricosDisciplinares(@RequestBody @Validated HistoricoDisciplinarRequestCreate historicoDisciplinarRequestCreate){

        historicoDisciplinarFacade.postHistoricoDisciplinar(historicoDisciplinarRequestCreate);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_CRIACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> putHistoricoDisciplinar(@RequestBody @Validated HistoricoDisciplinarRequestUpdate historicoDisciplinarRequestUpdate){

        historicoDisciplinarFacade.putHistoricoDisciplinar(historicoDisciplinarRequestUpdate);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_ATUALIZACAO.getMessage()).build(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteHistoricosDisciplinares(@RequestBody @Validated HistoricoDisciplinarRequestDelete historicoDisciplinarRequestDelete){

       historicoDisciplinarFacade.deleteHistoricoDisciplinar(historicoDisciplinarRequestDelete);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_DELETADAS.getMessage()).build(), HttpStatus.OK);
    }

}
