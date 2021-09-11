package br.com.fatec.sp.tcc.v1.orquestradorbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TiposVagasModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface TiposVagasRepository extends JpaRepository<TiposVagasModel, Long> {

    Optional<TiposVagasModel> findByTipo(String tipo);

}
