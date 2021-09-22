package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuarioRequestDelete;
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

    public List<UsuarioResponse> getUsuarios() {

        return usuarioMapper.mapUsuariosModelToUsuariosResponse(usuariosRepository.findAll());
    }

    public UsuarioResponse getUsuarioById(Long id) {
        try {

            return usuarioMapper.mapUsuarioModelToUsuarioResponse(usuariosRepository.findById(id).get());

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }
    }

    public UsuarioResponse getUsuarioByEmail(String email){
        try{
            return usuarioMapper.mapUsuarioModelToUsuarioResponse(usuariosRepository.findByEmail(email).get());

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }
    }

    public void postUsuarios(UsuariosRequestCreate usuariosRequestCreate) {
        try {
            usuariosRequestCreate.getRequest().stream().forEach(item -> {
                if (usuarioInexistente(item.getCpf())) {
                    UsuariosModel usuariosModel = usuarioMapper.mapCreateUsuarioRequestToUsuarioModel(item);
                    validarEndereco(item.getIdEndereco(), usuariosModel);
                    validarCurso(item.getIdcurso(), usuariosModel);
                    validarTurno(item.getIdturno(), usuariosModel);
                    usuariosRepository.save(usuariosModel);
                }
                ;
            });

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
        }
    }

    public void putUsuarios(UsuarioRequestUpdate usuarioRequestUpdate) {
        try {
            usuarioRequestUpdate.getRequest().forEach(item -> {
                Optional<UsuariosModel> usuario = usuariosRepository.findById(item.getIdUsuario());

                if (usuario.isPresent()) {
                    UsuariosModel usuariosModel = usuarioMapper.mapUpdateUsuarioRequestToUsuarioModel(item, usuario.get());
                    validarEndereco(item.getIdEndereco(), usuariosModel);
                    validarCurso(item.getIdcurso(), usuariosModel);
                    validarTurno(item.getIdturno(), usuariosModel);
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
                usuariosRepository.deleteById(item.getId());
            });

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_DELETE.getMessage() + e);
        }
    }

    private void validarCurso(Long idcurso, UsuariosModel usuariosModel) {

        Optional<CursosModel> cursoById = cursosRepository.findById(idcurso);

        if (cursoById.isPresent()) {
            usuariosModel.setCurso(cursoById.get());
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_curso"));
        }
    }

    private void validarEndereco(Long idEndereco, UsuariosModel usuariosModel) {

        Optional<EnderecosModel> enderecoById = enderecosRepository.findById(idEndereco);

        if (enderecoById.isPresent()) {

            List<EnderecosModel> listEndereco = new ArrayList<>();
            listEndereco.add(enderecoById.get());
            usuariosModel.setEnderecos(listEndereco);

        } else {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_endereco"));
        }
    }

    private void validarTurno(Long idturno, UsuariosModel usuariosModel) {

        Optional<TurnosModel> turnoById = turnoRepository.findById(idturno);

        if (turnoById.isPresent()) {
            usuariosModel.setTurno(turnoById.get());
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_turno"));
        }
    }


    private boolean usuarioInexistente(String cpf) {

        return usuariosRepository.findByCpf(cpf).isEmpty();
    }
}
