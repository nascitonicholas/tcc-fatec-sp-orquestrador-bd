package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TurnosResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.TurnosMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.TurnoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensErrorEnum.MESSAGE_ERROR_FIND;

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
}
