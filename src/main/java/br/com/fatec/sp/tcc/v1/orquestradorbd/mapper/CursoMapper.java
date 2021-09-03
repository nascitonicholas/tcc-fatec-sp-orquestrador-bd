package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.CursoResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.CursosModel;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper
public interface CursoMapper {

    CursoMapper MAPPER = Mappers.getMapper(CursoMapper.class);

    CursoResponse mapCursoModelToCursoResponse(CursosModel cursosModel);

    @Named("mapCursosModelToCursosResponse")
    default List<CursoResponse> mapCursosModelToCursosResponse(List<CursosModel> cursosModel){

        List<CursoResponse> response = new ArrayList<>();

        cursosModel.stream().forEach(item -> {
            if(Objects.nonNull(item)){
                response.add(mapCursoModelToCursoResponse(item));
            }
        });

        return response;
    }

}
