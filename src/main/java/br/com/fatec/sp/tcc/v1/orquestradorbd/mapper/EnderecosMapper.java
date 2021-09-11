package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.EnderecoRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.EnderecosRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.EnderecoResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.EnderecosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.EnderecosRequestCreate.*;
import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.EnderecoRequestUpdate.*;

@Mapper(imports = {Utils.class})
public interface EnderecosMapper {

    @Named("mapEnderecosModelToEnderecosResponse")
    default List<EnderecoResponse> mapEnderecosModelToEnderecosResponse(List<EnderecosModel> enderecosModel){

        List<EnderecoResponse> response = new ArrayList<>();

        enderecosModel.forEach(item ->{
            if(Objects.nonNull(item)){
                response.add(mapEnderecoModelToEnderecoResponse(item));
            }
        });

        return response;
    }

    EnderecoResponse mapEnderecoModelToEnderecoResponse(EnderecosModel enderecosModel);

    @Mappings({
            @Mapping(target = "logradouro", expression = "java(Utils.uppercase(requestCreate.getLogradouro()))"),
            @Mapping(target = "complemento", expression = "java(Utils.uppercase(requestCreate.getComplemento()))"),
            @Mapping(target = "bairro", expression = "java(Utils.uppercase(requestCreate.getBairro()))"),
            @Mapping(target = "municipio", expression = "java(Utils.uppercase(requestCreate.getMunicipio()))"),
            @Mapping(target = "estado", expression = "java(Utils.uppercase(requestCreate.getEstado()))"),
            @Mapping(target = "dataCriacao", expression = "java(Utils.dataAtualFormatada())")

    })
    EnderecosModel mapCreateEnderecosRequestToEnderecoModel(RequestCreate requestCreate);

    @Mappings({
            @Mapping(source = "enderecosModel.id", target = "id"),
            @Mapping(target = "logradouro", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getLogradouro(),enderecosModel.getLogradouro()))"),
            @Mapping(target = "numero", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getNumero(),enderecosModel.getNumero()))"),
            @Mapping(target = "complemento", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getComplemento(),enderecosModel.getComplemento()))"),
            @Mapping(target = "bairro", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getBairro(),enderecosModel.getBairro()))"),
            @Mapping(target = "municipio", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getMunicipio(),enderecosModel.getMunicipio()))"),
            @Mapping(target = "estado", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getEstado(),enderecosModel.getEstado()))"),
            @Mapping(target = "cep", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getCep(),enderecosModel.getCep()))"),
            @Mapping(target = "dataUltimaAlteracao", expression = "java(Utils.dataAtualFormatada())")

    })
    EnderecosModel mapUpdateEnderecoRequestToEnderecoModel(RequestUpdate requestUpdate, EnderecosModel enderecosModel);
}
