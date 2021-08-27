package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequest;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TiposVagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.TiposVagasMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TiposVagasModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.TiposVagasRepository;

@Component
public class TiposVagasFacade {

	@Autowired
	private TiposVagasRepository tiposVagasRespository;

	private TiposVagasMapper tiposVagasMapper;

	public List<TiposVagasResponse> getTiposVagas() {

		List<TiposVagasModel> listTiposVagasModel = tiposVagasRespository.findAll();

		List<TiposVagasResponse> response = new ArrayList<TiposVagasResponse>();

		listTiposVagasModel.stream().forEach(item -> {
			TiposVagasResponse itemResponse = tiposVagasMapper.mapTipoVagaModelToTipoVagaResponse(item);
			response.add(itemResponse);
		});

		return response;

	}

	public void postTiposVagas(List<TiposVagasRequest> tiposVagasRequest) {

		tiposVagasRequest.stream().forEach(item -> {

			tiposVagasRespository.save(tiposVagasMapper.mapTiposVagasRequestToTiposVagasModel(item));
		});

	}

}
