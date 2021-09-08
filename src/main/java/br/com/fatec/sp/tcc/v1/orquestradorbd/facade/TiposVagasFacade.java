package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequestDeleted;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequestUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TiposVagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.TiposVagasMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TiposVagasModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.TiposVagasRepository;

import static org.mapstruct.factory.Mappers.getMapper;

@Component
public class TiposVagasFacade {

    @Autowired
    private TiposVagasRepository tiposVagasRespository;

    TiposVagasMapper MAPPER = getMapper(TiposVagasMapper.class);

    public List<TiposVagasResponse> getTiposVagas() {

        List<TiposVagasModel> listTiposVagasModel = tiposVagasRespository.findAll();
        List<TiposVagasResponse> response = new ArrayList<>();

        listTiposVagasModel.forEach(item -> {
            TiposVagasResponse itemResponse = MAPPER.mapTipoVagaModelToTipoVagaResponse(item);
            response.add(itemResponse);
        });

        return response;

    }

    public void postTiposVagas(List<TiposVagasRequestCreate> tiposVagasRequestCreate) {

        tiposVagasRequestCreate.forEach(item -> {

            if (tipoVagaInexistente(item.getTipo())) {

                TiposVagasModel model = MAPPER.mapCreateTiposVagasRequestToTiposVagasModel(item);

                tiposVagasRespository.save(model);
            }

        });

    }

    public boolean tipoVagaInexistente(String nomeTipo) {

        return tiposVagasRespository.findByTipo(nomeTipo).isEmpty();
    }

    public Optional<TiposVagasModel> getTipoVagaById(Long id) {

        return tiposVagasRespository.findAllById(id);

    }

    public void putTiposVagas(List<TiposVagasRequestUpdate> tiposVagasRequestUpdate){

        tiposVagasRequestUpdate.forEach(item -> {
            Optional<TiposVagasModel> tipoVaga = getTipoVagaById(item.getId());
            if(tipoVaga.isPresent()){
                TiposVagasModel model = MAPPER.mapUpdateTiposVagasRequestToTiposVagasModel(item, tipoVaga.get());
                tiposVagasRespository.save(model);
            }

        });

    }

    public void deleteTiposVagas(List<TiposVagasRequestDeleted> tiposVagasRequestDeleteds) {

       tiposVagasRequestDeleteds.forEach(item -> {
           Optional<TiposVagasModel> tipoVagaById = getTipoVagaById(item.getId());
           if(tipoVagaById.isPresent()){
               tiposVagasRespository.deleteById(item.getId());
           }
       });
    }
}
