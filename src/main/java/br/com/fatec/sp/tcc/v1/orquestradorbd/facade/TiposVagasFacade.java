package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequest;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TiposVagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.TiposVagasMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TiposVagasModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.TiposVagasRepository;

import static org.mapstruct.factory.Mappers.getMapper;

@Component
public class TiposVagasFacade {

    @Autowired
    private TiposVagasRepository tiposVagasRespository;

    public List<TiposVagasResponse> getTiposVagas() {

        TiposVagasMapper MAPPER = getMapper(TiposVagasMapper.class);

        List<TiposVagasModel> listTiposVagasModel = tiposVagasRespository.findAll();
        List<TiposVagasResponse> response = new ArrayList<TiposVagasResponse>();

        listTiposVagasModel.stream().forEach(item -> {
            TiposVagasResponse itemResponse = MAPPER.mapTipoVagaModelToTipoVagaResponse(item);
            response.add(itemResponse);
        });

        return response;

    }

    public void postTiposVagas(List<TiposVagasRequest> tiposVagasRequest) {

        TiposVagasMapper MAPPER = getMapper(TiposVagasMapper.class);

        tiposVagasRequest.stream().forEach(item -> {

            if (tipoVagaInexistente(item.getTipo())) {

                TiposVagasModel model = MAPPER.mapCreateTiposVagasRequestToTiposVagasModel(item);

                tiposVagasRespository.save(model);
            }

        });

    }

    public boolean tipoVagaInexistente(String nomeTipo) {

        return tiposVagasRespository.findByTipo(nomeTipo).isEmpty() ? true : false;
    }

    public Optional<TiposVagasModel> getTipoVagaById(Long id) {

        Optional<TiposVagasModel> response = tiposVagasRespository.findAllById(id);

        return response;

    }
}
