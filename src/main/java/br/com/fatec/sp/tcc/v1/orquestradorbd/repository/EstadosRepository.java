package br.com.fatec.sp.tcc.v1.orquestradorbd.repository;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.EstadosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadosRepository extends JpaRepository<EstadosModel, Long> {
}
