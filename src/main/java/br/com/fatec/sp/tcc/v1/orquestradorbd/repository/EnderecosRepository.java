package br.com.fatec.sp.tcc.v1.orquestradorbd.repository;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.EnderecosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecosRepository extends JpaRepository<EnderecosModel, Long> {

   Optional<EnderecosModel> findByCep(String cep);
}
