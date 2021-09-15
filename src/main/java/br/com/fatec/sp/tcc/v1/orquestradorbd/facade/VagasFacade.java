package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.VagasRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.VagasRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.VagasRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.VagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.UsuarioMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.VagasMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.*;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensErrorEnum.*;

@Component
public class VagasFacade {

    @Autowired
    private VagasRepository vagasRepository;

    @Autowired
    private EnderecosRepository enderecosRepository;

    @Autowired
    private CursosRepository cursosRepository;

    @Autowired
    private TiposVagasRepository tiposVagasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    private final VagasMapper vagasMapper = Mappers.getMapper(VagasMapper.class);

    public List<VagasResponse> getVagas(){

        return vagasMapper.mapVagasModelToVagasResponse(vagasRepository.findAll());
    }

    public VagasResponse getById(Long id){

        try{

            return  vagasMapper.mapVagasModelToVagasResponse(vagasRepository.findById(id).get());

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }
    }

    public void postVagas(VagasRequestCreate vagasRequestCreate){
        try{

            vagasRequestCreate.getRequest().stream().forEach(item ->{
                VagasModel vagasModel = vagasMapper.mapCreateVagaRequestToVagaModel(item);
                validarCurso(item.getIdCurso(), vagasModel);
                validarUsuario(item.getIdUsuario(), vagasModel);
                validarTipoVaga(item.getIdTipoVaga(), vagasModel);
                validarEndereco(item.getIdEnderecoVaga(), vagasModel);
                vagasRepository.save(vagasModel);

            });

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
        }
    }

    public void putVagas(VagasRequestUpdate vagasRequestUpdate){
        try{

            vagasRequestUpdate.getRequest().stream().forEach(item ->{

                Optional<VagasModel> vagaModel = vagasRepository.findById(item.getId());

                if(vagaModel.isPresent()){
                    VagasModel vagasModel = vagasMapper.mapUpdateVagaRequestToVagaModel(item, vagaModel.get());
                    validarCurso(item.getIdCurso(), vagasModel);
                    validarUsuario(item.getIdUsuario(), vagasModel);
                    validarTipoVaga(item.getIdTipoVaga(), vagasModel);
                    validarEndereco(item.getIdEnderecoVaga(), vagasModel);
                    vagasRepository.save(vagasModel);
                }
            });

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_UPDATE.getMessage() + e);
        }
    }


    public void deleteVagas(VagasRequestDelete vagasRequestDelete){
        try {

            vagasRequestDelete.getRequest().forEach(item -> {
                vagasRepository.deleteById(item.getId());
            });

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_DELETE.getMessage() + e);
        }
    }

    private void validarCurso(Long idCurso, VagasModel vagasModel) {

        Optional<CursosModel> cursoById = cursosRepository.findById(idCurso);

        if (cursoById.isPresent()) {
            vagasModel.setCurso(cursoById.get());
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_curso"));
        }
    }

    private void validarEndereco(Long idEndereco, VagasModel vagasModel) {

        Optional<EnderecosModel> endereco = enderecosRepository.findById(idEndereco);

        if (endereco.isPresent()) {

            vagasModel.setEnderecoVaga(endereco.get());

        } else {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_endereco_vaga"));
        }
    }

    private void validarTipoVaga(Long idTipoVaga, VagasModel vagasModel) {

        Optional<TiposVagasModel> tipoVagas = tiposVagasRepository.findById(idTipoVaga);

        if(tipoVagas.isPresent()){
            vagasModel.setTipoVaga(tipoVagas.get());
        }else{
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_tipo_vaga"));
        }

    }

    private void validarUsuario(Long idUsuario, VagasModel vagasModel) {

        Optional<UsuariosModel> usuario = usuariosRepository.findById(idUsuario);

        if(usuario.isPresent()){
            vagasModel.setUsuario(usuario.get());
        }else{
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_usuario"));
        }
    }
}
