package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.EnderecoRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.EnderecoRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.EnderecosRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.EnderecoCreateUpdateResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.EnderecoResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.EnderecosFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum.*;


@RestController
@RequestMapping("enderecos")
public class EnderecosController  implements AbstractController<SaidaDefault> {

    @Autowired
    private EnderecosFacade enderecosFacade;

    @GetMapping
    public ResponseEntity<?> getEnderecos(){

        List<EnderecoResponse> enderecos = enderecosFacade.getEnderecos();

        return saidaSimplificada(SaidaDefault.builder().responseBody(enderecos).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnderecoById(@PathVariable Long id){

        EnderecoResponse endereco = enderecosFacade.getEnderecoById(id);

        return saidaSimplificada(SaidaDefault.builder().responseBody(endereco).message(MESSAGE_SUCESSO_ID.getMessage()).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postEnderecos(@RequestBody @Validated EnderecosRequestCreate enderecosRequestCreate) {

        List<EnderecoCreateUpdateResponse> response = enderecosFacade.postEnderecos(enderecosRequestCreate);

        return saidaSimplificada(SaidaDefault.builder().responseBody(response).message(MESSAGE_SUCESSO_CRIACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> putEnderecos(@RequestBody @Validated EnderecoRequestUpdate enderecoRequestUpdate){

        List<EnderecoCreateUpdateResponse> response = enderecosFacade.putEndereco(enderecoRequestUpdate);

        return saidaSimplificada(SaidaDefault.builder().responseBody(response).message(MESSAGE_SUCESSO_ATUALIZACAO.getMessage()).build(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEnderecos(@RequestBody @Validated EnderecoRequestDelete enderecoRequestDelete){

        enderecosFacade.deleteEnderecos(enderecoRequestDelete);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_DELETADAS.getMessage()).build(), HttpStatus.OK);
    }
}
