package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.HistoricoDisciplinarRequestCreate.HistoricoDisciplinarCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.HistoricoDisciplinarRequestUpdate.HistoricoDisciplinarUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.HistoricoDisciplinarResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.HistoricoDisciplinarModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(imports = {Utils.class})
public interface HistoricoDisciplinarMapper {

    @Mappings({
            @Mapping(source = "historicoDisciplinarModel.aluno", target = "aluno"),
            @Mapping(source = "historicoDisciplinarModel.idMateria", target = "materia")
    })
    HistoricoDisciplinarResponse mapHistoricoDisciplinarModelToHistoricoDisciplinarResponse (HistoricoDisciplinarModel historicoDisciplinarModel);

    @Mappings({
            @Mapping(target = "aluno", ignore = true),
            @Mapping(source = "historicoDisciplinarModel.idMateria", target = "materia")
    })
    HistoricoDisciplinarResponse mapHistoricoDisciplinarModelToHistoricoDisciplinarResponseByAluno (HistoricoDisciplinarModel historicoDisciplinarModel);

    @Named("mapHistoricosDisciplinaresModelToHistoricosDisciplinaresResponse")
    default List<HistoricoDisciplinarResponse> mapHistoricosDisciplinaresModelToHistoricosDisciplinaresResponse(List<HistoricoDisciplinarModel> historicoDisciplinarModel){

        List<HistoricoDisciplinarResponse> response = historicoDisciplinarModel.stream()
                .filter(item -> Objects.nonNull(item))
                .map(item -> mapHistoricoDisciplinarModelToHistoricoDisciplinarResponse(item)).collect(Collectors.toList());

        return response;
    }

    @Named("mapHistoricoDisciplinarModelToHistoricoResponseByAluno")
    default List<HistoricoDisciplinarResponse> mapHistoricoDisciplinarModelToHistoricoResponseByAluno(List<HistoricoDisciplinarModel> historicoDisciplinarModel){

        List<HistoricoDisciplinarResponse> response = historicoDisciplinarModel.stream()
                .filter(item -> Objects.nonNull(item))
                .map(item -> mapHistoricoDisciplinarModelToHistoricoDisciplinarResponseByAluno(item)).collect(Collectors.toList());

        return response;
    }

    @Mappings({
            @Mapping(target = "dataCriacao", expression = "java(Utils.dataAtualFormatada())"),
            @Mapping(target = "anoMesConclusao", expression = "java(Utils.uppercase(historicoDisciplinarCreate.getAnoMesConclusao()))"),
            @Mapping(target = "idMateria", ignore = true)
    })
    HistoricoDisciplinarModel mapHistoricoDisciplinarRequestToHistoricoDisciplinarModel(HistoricoDisciplinarCreate historicoDisciplinarCreate);

    @Mappings({
            @Mapping(source = "model.id", target = "id"),
            @Mapping(target = "anoMesConclusao", expression = "java(Utils.isNotNullOrEmpty(request.getAnoMesConclusao(),model.getAnoMesConclusao()))"),
            @Mapping(target = "faltas", expression = "java(Utils.isNotNullOrEmpty(request.getFaltas(),model.getFaltas()))"),
            @Mapping(target = "notaFinal", expression = "java(Utils.isNotNullOrEmpty(request.getNotaFinal(),model.getNotaFinal()))"),
            @Mapping(target = "conceito", expression = "java(Utils.isNotNullOrEmpty(request.getConceito(),model.getConceito()))"),
            @Mapping(target = "dataUltimaAlteracao", expression = "java(Utils.dataAtualFormatada())")
    })
    HistoricoDisciplinarModel mapUpdateHistoricoDisciplinarRequestToHistoricoDisciplinarModel(HistoricoDisciplinarUpdate request, HistoricoDisciplinarModel model);
}
