package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.EnderecoEstadoRequestCreate.*;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.EstadosResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.EstadosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(imports = {Utils.class})
public interface EstadosMapper {

    @Named("mapEnderecosModelToEnderecosResponse")
    default List<EstadosResponse> mapEnderecosModelToEnderecosResponse(List<EstadosModel> estadosModel){

        List<EstadosResponse> response = new ArrayList<>();

        estadosModel.forEach(item ->{
            if(Objects.nonNull(item)){
                response.add(mapEstadoModelToEstadoResponse(item));
            }
        });

        return  response;

    };

    @Mappings({
            @Mapping(target = "nome", expression = "java(Utils.uppercase(estadoRequest.getNome()))"),

    })
    EstadosModel mapEstadoRequestToEstadoModel(EstadoRequest estadoRequest);

    EstadosResponse mapEstadoModelToEstadoResponse(EstadosModel estadosModel);
}
