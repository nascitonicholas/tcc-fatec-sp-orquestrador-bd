package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.*;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.ProtocolosRematriculaResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.ProtocolosRematriculaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum.*;

@RestController
@RequestMapping("/protocoloRematricula")
public class ProtocolosRematriculaController implements AbstractController<SaidaDefault> {

    @Autowired
    private ProtocolosRematriculaFacade protocolosRematriculaFacade;

    @GetMapping
    public ResponseEntity<?> getProtolocosMatriculas(){

        List<ProtocolosRematriculaResponse> protocolosRematricula = protocolosRematriculaFacade.getProtocolosRematricula();

        return saidaSimplificada(SaidaDefault.builder().responseBody(protocolosRematricula).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProtocolosMatriculaById(@PathVariable Long id){

        ProtocolosRematriculaResponse protocolosRematriculaById = protocolosRematriculaFacade.getProtocolosRematriculaById(id);

        return saidaSimplificada(SaidaDefault.builder().responseBody(protocolosRematriculaById).message(MESSAGE_SUCESSO_ID.getMessage()).build(), HttpStatus.OK);

    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> getProtocoloRematriculaByUsuario(@PathVariable Long idUsuario){

        List<ProtocolosRematriculaResponse> protocolosRematriculasByUsuario = protocolosRematriculaFacade.getProtocolosRematriculasByUsuario(idUsuario);

        return saidaSimplificada(SaidaDefault.builder().responseBody(protocolosRematriculasByUsuario).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> postProtocolosRematriculas(@RequestBody @Validated ProtocolosRematriculaRequestCreate protocolosRematriculaRequestCreate){

        protocolosRematriculaFacade.postProtocolosRematricula(protocolosRematriculaRequestCreate);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_CRIACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> putVagas(@RequestBody @Validated ProtocolosRematriculaRequestUpdate protocolosRematriculaRequestUpdate){

        protocolosRematriculaFacade.putProtolocosRematriculas(protocolosRematriculaRequestUpdate);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_ATUALIZACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteVagas(@RequestBody @Validated ProtocolosRematriculaRequestDelete protocolosRematriculaRequestDelete){

        protocolosRematriculaFacade.deleteProcolosRematriculas(protocolosRematriculaRequestDelete);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_DELETADAS.getMessage()).build(), HttpStatus.OK);
    }
}
