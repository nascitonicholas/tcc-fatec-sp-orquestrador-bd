package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.ProtocolosRematriculaRequestCreate.RequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.ProtocolosRematriculaRequestUpdate.RequestUpdateProtocolo;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.ProtocolosRematriculaResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.ProtocolosRematriculaModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(imports = {Utils.class})
public interface ProtocolosRematriculasMapper {

    @Mappings({
            @Mapping(source = "protocolosRematriculaModel.aluno", target = "aluno")
    })
    ProtocolosRematriculaResponse mapProtocoloRematriculaModelToProtocoloRematriculaResponse(ProtocolosRematriculaModel protocolosRematriculaModel);

    @Named("mapProtocolosRematriculasModelToProtocolosRematriculasResponse")
    default List<ProtocolosRematriculaResponse> mapProtocolosRematriculasModelToProtocolosRematriculasResponse(List<ProtocolosRematriculaModel> protocolosRematriculaModels){

        List<ProtocolosRematriculaResponse> response = protocolosRematriculaModels.stream()
                .filter(item -> Objects.nonNull(item))
                .map(item -> mapProtocoloRematriculaModelToProtocoloRematriculaResponse(item)).collect(Collectors.toList());

        return response;
    }


    @Mappings({
            @Mapping(target = "dataCriacao", expression = "java(Utils.dataAtualFormatada())")
    })
    ProtocolosRematriculaModel mapCreateProtocoloRematriculaRequestToProtocoloRematriculaModel(RequestCreate requestCreate);


    @Mappings({
            @Mapping(source = "model.id", target = "id"),
            @Mapping(target = "anoSemestreRematricula", expression = "java(Utils.isNotNullOrEmpty(request.getAnoSemestreRematricula(),model.getAnoSemestreRematricula()))"),
            @Mapping(target = "dataUltimaAlteracao", expression = "java(Utils.dataAtualFormatada())")
    })
    ProtocolosRematriculaModel mapUpdateProtocoloRematriculaRequestToProtocoloRematriculaModel( RequestUpdateProtocolo request, ProtocolosRematriculaModel model);


}
