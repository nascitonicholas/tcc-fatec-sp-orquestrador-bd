package br.com.fatec.sp.tcc.v1.orquestradorbd.controller.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TiposVagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TiposVagasModel;

@Mapper
public interface TiposVagasMapper {
	
	TiposVagasResponse mapTipoVagaModelToTipoVagaResponse(TiposVagasModel tiposVagasModel);

	@Named("mapTiposVagasModelToTiposVagasResponse")
	default List<TiposVagasResponse> mapTiposVagasModelToTiposVagasResponse(List<TiposVagasModel> tiposVagasModel) {

		List<TiposVagasResponse> listResponse = new ArrayList<TiposVagasResponse>();

		tiposVagasModel.stream().forEach(item -> {

			if (Objects.nonNull(item))
				listResponse.add(mapTipoVagaModelToTipoVagaResponse(item));
		});

		return listResponse;
	}

}
