package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TurnoRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TurnoRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TurnoRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TurnoCreateUpdateResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TurnosResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.TurnosMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TurnosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.TurnoRepository;
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
public class TurnosFacade {

    @Autowired
    private TurnoRepository turnoRepository;

    private final TurnosMapper turnosMapper = Mappers.getMapper(TurnosMapper.class);

    public List<TurnosResponse> getTurnos(){

        return  turnosMapper.mapTurnosModelToTurnosResponse(turnoRepository.findAll());
    }

    public TurnosResponse getTurnoById(Long id){
        try{

            return turnosMapper.mapTurnoModelToTurnoResponse(turnoRepository.findById(id).get());

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }
    }

    public List<TurnoCreateUpdateResponse> postTurnos(TurnoRequestCreate turnoRequestCreate){

        List<TurnoCreateUpdateResponse> response = new ArrayList<>();

        try{

            turnoRequestCreate.getRequest().stream()
                    .filter(item -> turnoInexistente(item.getNome()))
                    .forEach(
                            item -> {
                                TurnosModel turnoModel = turnoRepository.save(turnosMapper.mapCreateTurnosRequestToTurnoModel(item));
                                response.add(turnosMapper.mapTurnoModelIdToTurnoIdResponse(turnoModel.getId()));
                            }
                    );

            return response;

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
        }
    }

    public List<TurnoCreateUpdateResponse> putTurnos(TurnoRequestUpdate requestUpdate){

        List<TurnoCreateUpdateResponse> response = new ArrayList<>();

        try{

            requestUpdate.getRequest().forEach(item -> {
                Optional<TurnosModel> optionalTurno = turnoRepository.findById(item.getId());
                if(optionalTurno.isPresent()){
                    TurnosModel turnoModel = turnoRepository.save(turnosMapper.mapUpdateTurnoRequestToTurnoModel(item, optionalTurno.get()));
                    response.add(turnosMapper.mapTurnoModelIdToTurnoIdResponse(turnoModel.getId()));
                }
            });

            return response;

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_UPDATE.getMessage() + e);
        }
    }

    public void deleteTurnos(TurnoRequestDelete turnoRequestDelete){
        try{
            turnoRequestDelete.getRequestDelete().forEach(item -> {
                turnoRepository.deleteById(item.getId());
            });

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_DELETE.getMessage() + e);
        }

    }

    private boolean turnoInexistente(String nome){
        return  turnoRepository.findByNome(nome).isEmpty();
    }
}
