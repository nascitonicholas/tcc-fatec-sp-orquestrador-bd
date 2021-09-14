package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuariosRequestCreate.RequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuariosRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.UsuarioResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.UsuariosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(imports = {Utils.class})
public interface UsuarioMapper {

    UsuarioResponse mapUsuarioModelToUsuarioResponse(UsuariosModel usuariosModel);

    @Named("mapUsuariosModelToUsuarioResponse")
    default List<UsuarioResponse> mapUsuariosModelToUsuariosResponse(List<UsuariosModel> usuariosModel){

        List<UsuarioResponse> response = usuariosModel.stream()
                .filter(item -> Objects.nonNull(item))
                .map(item -> mapUsuarioModelToUsuarioResponse(item)).collect(Collectors.toList());

        return response;
    }

    @Mappings({
            @Mapping(target = "dataCriacao", expression = "java(Utils.dataAtualFormatada())")
    })
    UsuariosModel mapCreateUsuarioRequestToUsuarioModel(RequestCreate requestCreate );


}
