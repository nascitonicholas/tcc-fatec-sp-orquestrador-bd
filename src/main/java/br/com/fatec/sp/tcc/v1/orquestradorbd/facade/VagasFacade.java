package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.VagasRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.VagasRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.VagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.UsuarioMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.VagasMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.VagasRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensErrorEnum.*;

@Component
public class VagasFacade {

    @Autowired
    private VagasRepository vagasRepository;

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

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
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
}
