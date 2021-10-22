package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuarioRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuarioRequestFind;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuarioRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuariosRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.UsuarioResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.UsuarioMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.CursosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.EnderecosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TurnosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.UsuariosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.CursosRepository;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.EnderecosRepository;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.TurnoRepository;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.UsuariosRepository;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuariosRequestCreate.*;
import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuarioRequestUpdate.*;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensErrorEnum.*;

@Component
public class UsuariosFacade {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private EnderecosRepository enderecosRepository;

    @Autowired
    private CursosRepository cursosRepository;

    @Autowired
    private TurnoRepository turnoRepository;

    private final UsuarioMapper usuarioMapper = Mappers.getMapper(UsuarioMapper.class);

    private UsuariosModel usuariosModel = new UsuariosModel();

    public List<UsuarioResponse> getUsuarios() {

        return usuarioMapper.mapUsuariosModelToUsuariosResponse(usuariosRepository.findAll());
    }

    public UsuarioResponse getUsuarioByNrMatricula(Long nrMatricula) {
        try {

            return usuarioMapper.mapUsuarioModelToUsuarioResponse(usuariosRepository.findByNrMatricula(nrMatricula).get());

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }
    }

    public void postUsuarios(UsuariosRequestCreate usuariosRequestCreate) {
        try {
            usuariosRequestCreate.getRequest().stream().forEach(item -> {
                if (usuarioInexistente(item.getCpf())) {
                    this.usuariosModel = usuarioMapper.mapCreateUsuarioRequestToUsuarioModel(item);
                    validarEndereco(item.getIdEndereco());
                    validarCurso(item.getIdcurso());
                    validarTurno(item.getIdturno());
                    usuariosRepository.save(usuariosModel);
                }
                ;
            });

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
        }
    }

    public UsuarioResponse getUsuarioByNrMatriculaAndSenha(UsuarioRequestFind requestFind) {

        try {

            return usuarioMapper.mapUsuarioModelToUsuarioResponse(usuariosRepository.findByNrMatriculaAndSenha(Long.parseLong(requestFind.getNrMatricula()), requestFind.getSenhaEncriptada()).get());

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }

    }

    public void putUsuarios(UsuarioRequestUpdate usuarioRequestUpdate) {
        try {
            usuarioRequestUpdate.getRequest().forEach(item -> {
                Optional<UsuariosModel> usuario = usuariosRepository.findByNrMatricula(item.getNrMatricula());

                if (usuario.isPresent()) {
                    UsuariosModel usuariosModel = usuarioMapper.mapUpdateUsuarioRequestToUsuarioModel(item, usuario.get());
                    validarEndereco(item.getIdEndereco());
                    validarCurso(item.getIdcurso());
                    validarTurno(item.getIdturno());
                    usuariosRepository.save(usuariosModel);
                }
            });

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_UPDATE.getMessage() + e.toString());
        }
    }

    public void deleteUsuarios(UsuarioRequestDelete usuarioRequestDelete) {
        try {
            usuarioRequestDelete.getRequest().forEach(item -> {
                usuariosRepository.deleteById(item.getNrMatricula());
            });

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_DELETE.getMessage() + e);
        }
    }

    private void validarCurso(Long idcurso) {

        Optional<CursosModel> cursoById = cursosRepository.findById(idcurso);

        if (cursoById.isPresent()) {
            this.usuariosModel.setCurso(cursoById.get());
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_curso"));
        }
    }

    private void validarEndereco(Long idEndereco) {

        Optional<EnderecosModel> enderecoById = enderecosRepository.findById(idEndereco);

        if (enderecoById.isPresent()) {

            List<EnderecosModel> listEndereco = new ArrayList<>();
            listEndereco.add(enderecoById.get());
            this.usuariosModel.setEnderecos(listEndereco);

        } else {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_endereco"));
        }
    }

    private void validarTurno(Long idturno) {

        Optional<TurnosModel> turnoById = turnoRepository.findById(idturno);

        if (turnoById.isPresent()) {
            this.usuariosModel.setTurno(turnoById.get());
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_turno"));
        }
    }


    private boolean usuarioInexistente(String cpf) {

        return usuariosRepository.findByCpf(cpf).isEmpty();
    }


}
