package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestCreate;
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
            @Mapping(target = "nome", expression = "java(Utils.uppercase(cursosRequestCreate.getNome()))"),
            @Mapping(target = "dataCriacao", expression = "java(Utils.dataAtualFormatada())")

    })
    CursosModel mapCreateCursosRequestToCursosModel(CursosRequestCreate cursosRequestCreate);

    @Mappings({
            @Mapping(source = "cursosModel.id", target = "id"),
            @Mapping(target = "nome", expression = "java(Utils.uppercase(cursosRequestUpdate.getNome()))"),
            @Mapping(target = "dataUltimaAlteracao", expression = "java(Utils.dataAtualFormatada())")

    })
    CursosModel mapUpdateCursosRequestToCursosModel(CursosRequestUpdate cursosRequestUpdate, CursosModel cursosModel);


}
