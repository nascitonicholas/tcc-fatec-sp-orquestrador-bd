package br.com.fatec.sp.tcc.v1.orquestradorbd.repository;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.EnderecosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecosRepository extends JpaRepository<EnderecosModel, Long> {

   Optional<EnderecosModel> findByCep(String cep);
}
