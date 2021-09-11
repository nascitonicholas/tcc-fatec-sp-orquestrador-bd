package br.com.fatec.sp.tcc.v1.orquestradorbd.repository;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.CursosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursosRepository extends JpaRepository<CursosModel, Long> {

    Optional<CursosModel> findByNome(String nome);
    Optional<CursosModel> findById(Long id);

}
