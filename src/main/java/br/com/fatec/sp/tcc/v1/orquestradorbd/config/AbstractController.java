package br.com.fatec.sp.tcc.v1.orquestradorbd.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface AbstractController<T> {

	default ResponseEntity<T> response(final T responseBody, final HttpStatus status) {
		return new ResponseEntity<>(responseBody, status);

	}
	
	default ResponseEntity<?> saidaVoid(final HttpStatus status) {
		return new ResponseEntity<Void>(status);

	}
	
	default ResponseEntity<?> saidaSimplificada(final T responseBody, final HttpStatus status) {
		
		return ResponseEntity.status(status).body(responseBody);
	}
}
