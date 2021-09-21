package br.com.fatec.sp.tcc.v1.orquestradorbd.mapper;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.MateriasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.MateriasModel;
import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.MateriasRequestCreate.MateriasCreate;
import static br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.MateriasRequestUpdate.MateriasUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(imports = {Utils.class})
public interface MateriasMapper {

    @Mappings({
            @Mapping(source = "materiasModel.professores", target = "professores")
    })
    MateriasResponse mapMateriasModelToMateriasResponse(MateriasModel materiasModel);

    @Named("mapMateriasModelToMateriasResponse")
    default List<MateriasResponse> mapMateriasModelToMateriasResponse(List<MateriasModel> materiasModel){

        List<MateriasResponse> response = materiasModel.stream()
                .filter(item -> Objects.nonNull(item))
                .map(item -> mapMateriasModelToMateriasResponse(item)).collect(Collectors.toList());

        return response;

    }

    @Mappings({
            @Mapping(target = "dataCriacao", expression = "java(Utils.dataAtualFormatada())"),
            @Mapping(target = "nome", expression = "java(Utils.uppercase(materiasCreate.getNome()))"),
            @Mapping(target = "semestre", expression = "java(Utils.uppercase(materiasCreate.getSemestre()))"),
    })
    MateriasModel mapCreateMateriaRequestToMateriasModel(MateriasCreate materiasCreate);



    @Mappings({
            @Mapping(source = "materiasModel.id", target = "id"),
            @Mapping(target = "nome", expression = "java(Utils.isNotNullOrEmpty(materiasUpdate.getNome(),materiasModel.getNome()))"),
            @Mapping(target = "codMateria", expression = "java(Utils.isNotNullOrEmpty(materiasUpdate.getCodMateria(),materiasModel.getCodMateria()))"),
            @Mapping(target = "semestre", expression = "java(Utils.isNotNullOrEmpty(materiasUpdate.getSemestre(),materiasModel.getSemestre()))"),
            @Mapping(target = "dataUltimaAlteracao", expression = "java(Utils.dataAtualFormatada())")
    })
    MateriasModel mapUpdateMateriasRequestToMateriasModel(MateriasUpdate materiasUpdate, MateriasModel materiasModel);

}
