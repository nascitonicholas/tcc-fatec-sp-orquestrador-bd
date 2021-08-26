package br.com.fatec.sp.tcc.v1.orquestradorbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.TiposVagasModel;

@Repository
public interface TipoVagasRepository extends JpaRepository<TiposVagasModel, Long> {

}
