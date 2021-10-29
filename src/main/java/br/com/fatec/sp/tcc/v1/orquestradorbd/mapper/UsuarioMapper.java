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

@Mapper(imports = {Utils.class,Objects.class})
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
//            @Mapping(target = "nrMatricula", expression = "requestcreate.getNrMatricula()"),
            @Mapping(target = "dataCriacao", expression = "java(Utils.dataAtualFormatada())"),
            @Mapping(target = "nome", expression = "java(Utils.uppercase(requestCreate.getNome()))"),
            @Mapping(target = "email", expression = "java(Utils.uppercase(requestCreate.getEmail()))"),
            @Mapping(target = "senha", expression = "java(Utils.encodeSenha(requestCreate.getSenha()))")
    })
    UsuariosModel mapCreateUsuarioRequestToUsuarioModel(RequestCreate requestCreate );


    @Mappings({
            @Mapping(source = "usuariosModel.nrMatricula", target = "nrMatricula"),
            @Mapping(target = "nome", expression = "java(Objects.nonNull(requestUpdate.getNome()) ? requestUpdate.getNome() : usuariosModel.getNome())"),
            @Mapping(target = "nomeMae", expression = "java(Objects.nonNull(requestUpdate.getNomeMae()) ? requestUpdate.getNomeMae() : usuariosModel.getNomeMae())"),
            @Mapping(target = "nomePai", expression = "java(Objects.nonNull(requestUpdate.getNomePai()) ? requestUpdate.getNomePai() : usuariosModel.getNomePai())"),
            @Mapping(target = "email", expression = "java(Objects.nonNull(requestUpdate.getEmail()) ? requestUpdate.getEmail() :usuariosModel.getEmail())"),
            @Mapping(target = "emailPessoal", expression = "java(Objects.nonNull(requestUpdate.getEmailPessoal()) ? requestUpdate.getEmailPessoal() : usuariosModel.getEmailPessoal())"),
            @Mapping(target = "senha", expression = "java(Utils.verificarSenha(requestUpdate,usuariosModel))"),
            @Mapping(target = "cpf", expression = "java(Objects.nonNull(requestUpdate.getCpf()) ? requestUpdate.getCpf() : usuariosModel.getCpf())"),
            @Mapping(target = "rg", expression = "java(Objects.nonNull(requestUpdate.getRg()) ? requestUpdate.getRg() : usuariosModel.getRg())"),
            @Mapping(target = "certificadoMilitar", expression = "java(Objects.nonNull(requestUpdate.getCertificadoMilitar()) ? requestUpdate.getCertificadoMilitar() : usuariosModel.getCertificadoMilitar())"),
            @Mapping(target = "numeroTitulo", expression = "java(Objects.nonNull(requestUpdate.getNumeroTitulo()) ? requestUpdate.getNumeroTitulo() : usuariosModel.getNumeroTitulo())"),
            @Mapping(target = "zonaTitulo", expression = "java(Objects.nonNull(requestUpdate.getZonaTitulo()) ? requestUpdate.getZonaTitulo() : usuariosModel.getZonaTitulo())"),
            @Mapping(target = "telefone", expression = "java(Objects.nonNull(requestUpdate.getTelefone()) ? requestUpdate.getTelefone() :  usuariosModel.getTelefone())"),
            @Mapping(target = "celular", expression = "java(Objects.nonNull(requestUpdate.getCelular()) ? requestUpdate.getCelular() : usuariosModel.getCelular())"),
            @Mapping(target = "dataUltimaAlteracao", expression = "java(Utils.dataAtualFormatada())")
    })
    UsuariosModel mapUpdateUsuarioRequestToUsuarioModel(RequestUpdate requestUpdate, UsuariosModel usuariosModel);


}
