package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.EnderecoEstadoRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.EnderecoRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.EnderecoRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.EnderecosRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.EnderecoCreateUpdateResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.EnderecoCreateUpdateResponse.*;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.EnderecoResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.EstadosResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.EnderecosMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.EstadosMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.EnderecosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.EstadosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.EnderecosRepository;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.EstadosRepository;
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
public class EnderecosFacade {

    @Autowired
    private EnderecosRepository enderecosRepository;

    @Autowired
    private EstadosRepository estadosRepository;

    private final EnderecosMapper enderecosMapper = Mappers.getMapper(EnderecosMapper.class);

    private final EstadosMapper estadosMapper = Mappers.getMapper(EstadosMapper.class);

    public List<EnderecoResponse> getEnderecos() {

        List<EnderecosModel> listaEnderecos = enderecosRepository.findAll();

        return enderecosMapper.mapEnderecosModelToEnderecosResponse(listaEnderecos);
    }

    public EnderecoResponse getEnderecoById(Long id) {
        try {

            EnderecosModel enderecosModel = enderecosRepository.findById(id).get();

            return enderecosMapper.mapEnderecoModelToEnderecoResponse(enderecosModel);

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }
    }

    public List<EstadosResponse> getEstados() {

        List<EstadosModel> listaEstados = estadosRepository.findAll();

        return estadosMapper.mapEnderecosModelToEnderecosResponse(listaEstados);
    }

    public List<EnderecoCreateUpdateResponse> postEnderecos(EnderecosRequestCreate enderecosRequestCreate) {

        List<EnderecoCreateUpdateResponse> response = new ArrayList<>();

        try {
            enderecosRequestCreate.getRequest().forEach(item -> {
                if (enderecoInexistente(item.getCep())) {

                    EnderecosModel enderecoEntity = enderecosMapper.mapCreateEnderecosRequestToEnderecoModel(item);
                    validarEstado(enderecoEntity,item.getIdEstado());
                    EnderecosModel endereco = enderecosRepository.save(enderecoEntity);

                    response.add(enderecosMapper.mapEnderecoModelIdToEnderecoResponseId(endereco.getId()));
                }
            });


            return response;

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
        }
    }

    private void validarEstado(EnderecosModel endereco, Long idEstado) {

        Optional<EstadosModel> estado = estadosRepository.findById(idEstado);

        if(estado.isPresent()){

            endereco.setEstado(estado.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_estado"));
        }

    }

    public void postEstados(EnderecoEstadoRequestCreate enderecoEstadoRequestCreate) {

        try {
            enderecoEstadoRequestCreate.getRequest().forEach(item ->{
                estadosRepository.save(estadosMapper.mapEstadoRequestToEstadoModel(item));
            });

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
        }
    }

    public List<EnderecoCreateUpdateResponse> putEndereco(EnderecoRequestUpdate enderecoRequestUpdate) {

        List<EnderecoCreateUpdateResponse> response = new ArrayList<>();

        try {
            enderecoRequestUpdate.getRequest().forEach(item -> {
                Optional<EnderecosModel> endereco = enderecosRepository.findById(item.getId());
                if (endereco.isPresent()) {
                    EnderecosModel enderecosModel = enderecosMapper.mapUpdateEnderecoRequestToEnderecoModel(item, endereco.get());
                    enderecosRepository.save(enderecosModel);
                    response.add(enderecosMapper.mapEnderecoModelIdToEnderecoResponseId(enderecosModel.getId()));
                }
            });

            return response;

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_UPDATE.getMessage() + e);
        }
    }

    public void deleteEnderecos(EnderecoRequestDelete enderecoRequestDelete) {

        try {
            enderecoRequestDelete.getRequestDelete().forEach(item -> {
                Optional<EnderecosModel> enderecoById = enderecosRepository.findById(item.getId());
                if (enderecoById.isPresent()) {
                    enderecosRepository.deleteById(item.getId());
                }
            });

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_DELETE.getMessage() + e);
        }

    }


    private boolean enderecoInexistente(String cep) {

        return enderecosRepository.findByCep(cep).isEmpty();
    }



}
