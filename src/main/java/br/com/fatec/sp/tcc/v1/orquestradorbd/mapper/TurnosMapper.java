package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TurnosResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TurnosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

}
