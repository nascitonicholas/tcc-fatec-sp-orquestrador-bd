package br.com.fatec.sp.tcc.v1.orquestradorbd.repository;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TurnosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<TurnosModel,Long> {

   Optional<TurnosModel> findByNome(String nome);
   Optional<TurnosModel> findById(Long id);
}
