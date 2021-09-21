package br.com.fatec.sp.tcc.v1.orquestradorbd.repository;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.HistoricoDisciplinarModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoricoDisciplinarRepository extends JpaRepository<HistoricoDisciplinarModel,Long> {

    Optional<List<HistoricoDisciplinarModel>> findByAluno(UsuariosModel usuariosModel);
}
