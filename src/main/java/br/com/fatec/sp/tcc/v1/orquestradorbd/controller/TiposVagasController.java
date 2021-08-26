package br.com.fatec.sp.tcc.v1.orquestradorbd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.sp.tcc.v1.orquestradorbd.config.AbstractController;
import br.com.fatec.sp.tcc.v1.orquestradorbd.config.SaidaDefault;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.TiposVagasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.facade.TiposVagasFacade;

@RestController
@RequestMapping("/tipos_vagas")
public class TiposVagasController implements AbstractController<SaidaDefault> {

	@Autowired
	private TiposVagasFacade tiposVagasFacade;

	@GetMapping
	public ResponseEntity<SaidaDefault> getTiposVagas() {

		List<TiposVagasResponse> response = tiposVagasFacade.getTiposVagas();

		return ResponseEntity.ok(SaidaDefault.builder().responseBody(response).build());

	}
}
