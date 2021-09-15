package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

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

import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuarioRequestUpdate.RequestUpdate;
import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.UsuariosRequestCreate.RequestCreate;

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
            @Mapping(target = "dataCriacao", expression = "java(Utils.dataAtualFormatada())"),
            @Mapping(target = "nome", expression = "java(Utils.uppercase(requestCreate.getNome()))"),
            @Mapping(target = "email", expression = "java(Utils.uppercase(requestCreate.getEmail()))"),
    })
    UsuariosModel mapCreateUsuarioRequestToUsuarioModel(RequestCreate requestCreate );


    @Mappings({
            @Mapping(source = "usuariosModel.id", target = "id"),
            @Mapping(target = "nome", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getNome(),usuariosModel.getNome()))"),
            @Mapping(target = "email", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getEmail(),usuariosModel.getEmail()))"),
            @Mapping(target = "cpf", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getCpf(),usuariosModel.getCpf()))"),
            @Mapping(target = "rg", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getRg(),usuariosModel.getRg()))"),
            @Mapping(target = "certificadoMilitar", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getCertificadoMilitar(),usuariosModel.getCertificadoMilitar()))"),
            @Mapping(target = "numeroTitulo", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getNumeroTitulo(),usuariosModel.getNumeroTitulo()))"),
            @Mapping(target = "zonaTitulo", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getZonaTitulo(),usuariosModel.getZonaTitulo()))"),
            @Mapping(target = "telefone", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getTelefone(),usuariosModel.getTelefone()))"),
            @Mapping(target = "celular", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getCelular(),usuariosModel.getCelular()))"),
            @Mapping(target = "dataUltimaAlteracao", expression = "java(Utils.dataAtualFormatada())")
    })
    UsuariosModel mapUpdateUsuarioRequestToUsuarioModel(RequestUpdate requestUpdate, UsuariosModel usuariosModel);


}
