package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;


import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequestDeleted;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TiposVagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.TiposVagasMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TiposVagasModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.TiposVagasRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensErrorEnum.*;

@Component
public class TiposVagasFacade {

    @Autowired
    private TiposVagasRepository tiposVagasRespository;

    private final TiposVagasMapper tiposVagasMapper = Mappers.getMapper(TiposVagasMapper.class);


    public List<TiposVagasResponse> getTiposVagas() {

        List<TiposVagasModel> listTiposVagas = tiposVagasRespository.findAll();

        return tiposVagasMapper.mapTiposVagasModelToTiposVagasResponse(listTiposVagas);

    }

    public void postTiposVagas(List<TiposVagasRequestCreate> tiposVagasRequestCreate) {

        try {
            tiposVagasRequestCreate.forEach(item -> {

                if (tipoVagaInexistente(item.getTipo())) {

                    TiposVagasModel model = tiposVagasMapper.mapCreateTiposVagasRequestToTiposVagasModel(item);

                    tiposVagasRespository.save(model);
                }
            });
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
        }
    }

    public boolean tipoVagaInexistente(String nomeTipo) {

        return tiposVagasRespository.findByTipo(nomeTipo).isEmpty();
    }

    public TiposVagasResponse getTipoVagaById(Long id) {

        try {
            TiposVagasModel tiposVagas = tiposVagasRespository.findById(id).get();
            return tiposVagasMapper.mapTipoVagaModelToTipoVagaResponse(tiposVagas);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }


    }

    public void putTiposVagas(List<TiposVagasRequestUpdate> tiposVagasRequestUpdate) {

        try {
            tiposVagasRequestUpdate.forEach(item -> {
                Optional<TiposVagasModel> tipoVaga = tiposVagasRespository.findById(item.getId());
                if (tipoVaga.isPresent()) {
                    TiposVagasModel model = tiposVagasMapper.mapUpdateTiposVagasRequestToTiposVagasModel(item, tipoVaga.get());
                    tiposVagasRespository.save(model);
                }
            });
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_UPDATE.getMessage() + e);
        }
    }

    public void deleteTiposVagas(List<TiposVagasRequestDeleted> tiposVagasRequestDeleteds) {

        try {

            tiposVagasRequestDeleteds.forEach(item -> {
                if (Objects.nonNull(item.getId())) {
                    tiposVagasRespository.deleteById(item.getId());
                }
            });

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_DELETE.getMessage() + e);
        }

    }
}
