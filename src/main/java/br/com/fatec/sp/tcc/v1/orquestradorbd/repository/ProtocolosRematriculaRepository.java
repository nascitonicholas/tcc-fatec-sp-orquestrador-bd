package br.com.fatec.sp.tcc.v1.orquestradorbd.repository;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.ProtocolosRematriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProtocolosRematriculaRepository extends JpaRepository<ProtocolosRematriculaModel, Long> {

    Optional<ProtocolosRematriculaModel> findByCodProtrocolo(String codProtocolo);
}
