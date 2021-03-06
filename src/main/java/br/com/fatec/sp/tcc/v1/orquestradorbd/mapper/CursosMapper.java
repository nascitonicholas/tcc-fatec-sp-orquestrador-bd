package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.CursoCreateUpdateResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.CursoResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.CursosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestUpdate.*;
import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestCreate.*;

@Mapper(imports = {Utils.class})
public interface CursosMapper {

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

    @Mappings({
            @Mapping(target = "nome", expression = "java(Utils.uppercase(requestCreate.getNome()))"),
            @Mapping(target = "dataCriacao", expression = "java(Utils.dataAtualFormatada())")

    })
    CursosModel mapCreateCursosRequestToCursosModel(RequestCreate requestCreate);

    @Mappings({
            @Mapping(source = "cursosModel.id", target = "id"),
            @Mapping(target = "nome", expression = "java(Utils.uppercase(request.getNome()))"),
            @Mapping(target = "dataUltimaAlteracao", expression = "java(Utils.dataAtualFormatada())")

    })
    CursosModel mapUpdateCursosRequestToCursosModel(RequestUpdate request, CursosModel cursosModel);


    CursoCreateUpdateResponse mapIdCursoModelToIdCursoResponse(Long id);

}
