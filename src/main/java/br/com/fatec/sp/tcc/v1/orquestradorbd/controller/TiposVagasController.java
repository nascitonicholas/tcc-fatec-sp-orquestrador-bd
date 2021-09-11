package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequestDeleted;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TiposVagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.TiposVagasFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum.*;

@RestController
@RequestMapping("/tipos_vagas")
public class TiposVagasController implements AbstractController<SaidaDefault> {

    @Autowired
    private TiposVagasFacade tiposVagasFacade;

    @GetMapping
    public ResponseEntity<?> getTiposVagas() {

        List<TiposVagasResponse> tiposVagas = tiposVagasFacade.getTiposVagas();

        return saidaSimplificada(SaidaDefault.builder().responseBody(tiposVagas).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> postTiposVagas(@RequestBody List<TiposVagasRequestCreate> tiposVagasRequestCreate) {

        tiposVagasFacade.postTiposVagas(tiposVagasRequestCreate);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_CRIACAO.getMessage()).build(), HttpStatus.CREATED);

    }

    @GetMapping("/{Id}")
    public ResponseEntity<?> getTipoVagaById(@PathVariable Long Id) {

        TiposVagasResponse tipoVaga = tiposVagasFacade.getTipoVagaById(Id);

        return saidaSimplificada(SaidaDefault.builder().responseBody(tipoVaga).message(MESSAGE_SUCESSO_ID.getMessage()).build(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> putTiposVagas(@RequestBody List<TiposVagasRequestUpdate> tiposVagasRequestUpdateList) {

        tiposVagasFacade.putTiposVagas(tiposVagasRequestUpdateList);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_ATUALIZACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTiposVagas(@RequestBody List<TiposVagasRequestDeleted> tiposVagasRequestDeleteds) {

        tiposVagasFacade.deleteTiposVagas(tiposVagasRequestDeleteds);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_DELETADAS.getMessage()).build(), HttpStatus.OK);
    }

}
