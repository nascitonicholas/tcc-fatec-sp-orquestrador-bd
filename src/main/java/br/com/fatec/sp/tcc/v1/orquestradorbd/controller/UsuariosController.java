package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuarioRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuarioRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuariosRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.UsuarioResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.UsuariosFacade;
import br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensEnum.*;

@RestController
@RequestMapping("usuarios")
public class UsuariosController implements AbstractController<SaidaDefault> {

    @Autowired
    private UsuariosFacade usuariosFacade;

    @GetMapping
    public ResponseEntity<?> getUsuarios(){

        List<UsuarioResponse> usuarios = usuariosFacade.getUsuarios();

        return saidaSimplificada(SaidaDefault.builder().responseBody(usuarios).message(MESSAGE_SUCESSO_LISTA.getMessage()).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuariosById(@PathVariable Long id){

        UsuarioResponse usuario = usuariosFacade.getUsuarioById(id);

        return saidaSimplificada(SaidaDefault.builder().responseBody(usuario).message(MESSAGE_SUCESSO_ID.getMessage()).build(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> postUsuarios(@RequestBody @Validated UsuariosRequestCreate usuariosRequestCreate){

        usuariosFacade.postUsuarios(usuariosRequestCreate);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_CRIACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> putUsuarios(@RequestBody @Validated UsuarioRequestUpdate usuarioRequestUpdate){

        usuariosFacade.putUsuarios(usuarioRequestUpdate);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_ATUALIZACAO.getMessage()).build(), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUsuarios(@RequestBody @Validated UsuarioRequestDelete usuarioRequestDelete){

        usuariosFacade.deleteUsuarios(usuarioRequestDelete);

        return saidaSimplificada(SaidaDefault.builder().message(MESSAGE_SUCESSO_DELETADAS.getMessage()).build(), HttpStatus.OK);
    }

}
