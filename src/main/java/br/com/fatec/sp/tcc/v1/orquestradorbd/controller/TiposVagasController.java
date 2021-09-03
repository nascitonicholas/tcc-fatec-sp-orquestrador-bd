package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import java.util.List;
import java.util.Optional;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TiposVagasModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequest;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TiposVagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.TiposVagasFacade;

@RestController
@RequestMapping("/tipos_vagas")
public class TiposVagasController implements AbstractController<SaidaDefault> {

    @Autowired
    private TiposVagasFacade tiposVagasFacade;

    @GetMapping
    public ResponseEntity<?> getTiposVagas() {

        List<TiposVagasResponse> response = tiposVagasFacade.getTiposVagas();

        return saidaSimplificada(SaidaDefault.builder().responseBody(response).message("Lista Retornada com Sucesso").build(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> postTiposVagas(@RequestBody List<TiposVagasRequest> tiposVagasRequest) {

        tiposVagasFacade.postTiposVagas(tiposVagasRequest);

        return saidaVoid(HttpStatus.CREATED);


    }

    @GetMapping("/{Id}")
    public ResponseEntity<?> getTipoVagaById(@PathVariable Long Id) {

        Optional<TiposVagasModel> response = tiposVagasFacade.getTipoVagaById(Id);

        if (response.isPresent()) {

            return saidaSimplificada(SaidaDefault.builder().responseBody(response).message("Tipo Encontrado").build(), HttpStatus.OK);
        } else {
            return saidaVoid(HttpStatus.NOT_FOUND);
        }


    }

}
