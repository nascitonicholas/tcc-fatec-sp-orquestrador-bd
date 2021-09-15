package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.VagasRequestCreate.RequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.VagasRequestUpdate.RequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.VagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.VagasModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(imports = {Utils.class})
public interface VagasMapper {

    VagasResponse mapVagasModelToVagasResponse(VagasModel vagasModel);

    @Named("mapVagasModelToVagasResponse")
    default List<VagasResponse> mapVagasModelToVagasResponse(List<VagasModel> vagasModels){

        return vagasModels.stream()
                .filter(Objects::nonNull)
                .map(this::mapVagasModelToVagasResponse).collect(Collectors.toList());

    }

    @Mappings({
            @Mapping(target = "dataCriacao", expression = "java(Utils.dataAtualFormatada())")
    })
    VagasModel mapCreateVagaRequestToVagaModel(RequestCreate requestCreate);


    @Mappings({

            @Mapping(source = "vagasModel.id"  , target = "id"),
            @Mapping(target = "titulo", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getTitulo(),vagasModel.getTitulo()))"),
            @Mapping(target = "dataVencimento", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getDataVencimento(),vagasModel.getDataVencimento()))"),
            @Mapping(target = "horario", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getHorario(),vagasModel.getHorario()))"),
            @Mapping(target = "numeroVagas", expression = "java(Utils.numeroDivergente(requestUpdate.getNumeroVagas(),vagasModel.getNumeroVagas()))"),
            @Mapping(target = "remuneracao", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getRemuneracao(),vagasModel.getRemuneracao()))"),
            @Mapping(target = "atividades", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getAtividades(),vagasModel.getAtividades()))"),
            @Mapping(target = "periodoFaculdade", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getPeriodoFaculdade(),vagasModel.getPeriodoFaculdade()))"),
            @Mapping(target = "conhecimentosInformatica", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getConhecimentosInformatica(),vagasModel.getConhecimentosInformatica()))"),
            @Mapping(target = "conhecimentosLinguas", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getConhecimentosLinguas(),vagasModel.getConhecimentosLinguas()))"),
            @Mapping(target = "observacoes", expression = "java(Utils.isNotNullOrEmpty(requestUpdate.getObservacoes(),vagasModel.getObservacoes()))"),
            @Mapping(target = "dataUltimaAlteracao", expression = "java(Utils.dataAtualFormatada())")
    })
    VagasModel mapUpdateVagaRequestToVagaModel(RequestUpdate requestUpdate, VagasModel vagasModel);

}
