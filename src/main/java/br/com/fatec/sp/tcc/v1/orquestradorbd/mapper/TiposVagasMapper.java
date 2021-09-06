package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequest;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TiposVagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TiposVagasModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;

@Mapper(imports = {Utils.class})
public interface TiposVagasMapper {



    TiposVagasResponse mapTipoVagaModelToTipoVagaResponse(TiposVagasModel tiposVagasModel);

    @Named("mapTiposVagasModelToTiposVagasResponse")
    default List<TiposVagasResponse> mapTiposVagasModelToTiposVagasResponse(List<TiposVagasModel> tiposVagasModel) {

        List<TiposVagasResponse> listResponse = new ArrayList<TiposVagasResponse>();

        tiposVagasModel.stream().forEach(item -> {

            if (Objects.nonNull(item))
                listResponse.add(mapTipoVagaModelToTipoVagaResponse(item));
        });

        return listResponse;
    }

    @Mappings({
            @Mapping(target = "tipo", expression = "java(Utils.uppercase(tiposVagasRequest.getTipo()))"),
            @Mapping(target = "dataCriacao", expression = "java(Utils.dataAtualFormatada())")

    })
    TiposVagasModel mapCreateTiposVagasRequestToTiposVagasModel(TiposVagasRequest tiposVagasRequest);

    @Mappings({
            @Mapping(target = "tipo", expression = "java(Utils.uppercase(tiposVagasRequest.getTipo()))"),
            @Mapping(target = "dataUltimaAlteracao", expression = "java(Utils.dataAtualFormatada())")

    })
    TiposVagasModel mapUpdateTiposVagasRequestToTiposVagasModel(TiposVagasRequest tiposVagasRequest);

}
