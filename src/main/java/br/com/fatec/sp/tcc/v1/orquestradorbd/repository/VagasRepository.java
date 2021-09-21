package br.com.fatec.sp.tcc.v1.orquestradorbd.repository;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.CursosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.VagasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VagasRepository extends JpaRepository<VagasModel, Long> {

    Optional<VagasModel> findById(Long id);
    Optional<List<VagasModel>> findByCurso(CursosModel cursosModel);
}
