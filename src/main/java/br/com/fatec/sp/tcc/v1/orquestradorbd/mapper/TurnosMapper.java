package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TurnoRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TurnoRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TurnoCreateUpdateResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TurnosResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TurnosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TurnoRequestCreate.*;
import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TurnoRequestUpdate.*;

@Mapper(imports = {Utils.class})
public interface TurnosMapper {

    TurnosResponse mapTurnoModelToTurnoResponse(TurnosModel turnosModel);

    @Named("mapTurnosModelToCursosResponse")
    default List<TurnosResponse> mapTurnosModelToTurnosResponse(List<TurnosModel> turnosModel){

        List<TurnosResponse> response = turnosModel.stream()
                .filter(item -> Objects.nonNull(item))
                .map(item -> mapTurnoModelToTurnoResponse(item)).collect(Collectors.toList());
        return response;
    }

    @Mappings({
            @Mapping(target = "nome", expression = "java(Utils.uppercase(requestCreate.getNome()))"),
            @Mapping(target = "dataCriacao", expression = "java(Utils.dataAtualFormatada())")
    })
    TurnosModel mapCreateTurnosRequestToTurnoModel(RequestCreate requestCreate);


    @Mappings({
            @Mapping(source = "turnosModel.id", target = "id"),
            @Mapping(target = "nome", expression = "java(Utils.uppercase(requestUpdate.getNome()))"),
            @Mapping(target = "dataUltimaAlteracao", expression = "java(Utils.dataAtualFormatada())")
    })
    TurnosModel mapUpdateTurnoRequestToTurnoModel(RequestUpdate requestUpdate, TurnosModel turnosModel);

    TurnoCreateUpdateResponse mapTurnoModelIdToTurnoIdResponse(Long id);

}
