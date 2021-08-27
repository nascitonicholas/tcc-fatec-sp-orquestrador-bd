package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.TiposVagasRequest;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TiposVagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.TiposVagasFacade;

@RestController
@RequestMapping("/tipos_vagas")
public class TiposVagasController implements AbstractController<SaidaDefault> {

	@Autowired
	private TiposVagasFacade tiposVagasFacade;

	@GetMapping
	public ResponseEntity<?> getTiposVagas() {

		List<TiposVagasResponse> response = tiposVagasFacade.getTiposVagas();

		return saidaSimplificada(SaidaDefault.builder().responseBody(response).message("Lista Retornada com Sucesso").build(), HttpStatus.OK);

	}
	
	@PostMapping
	public ResponseEntity<?> postTiposVagas(@RequestBody List<TiposVagasRequest> tiposVagasRequest){
		
		tiposVagasFacade.postTiposVagas(tiposVagasRequest);
		
		return saidaVoid(HttpStatus.CREATED);
		
		
	}
	
}
