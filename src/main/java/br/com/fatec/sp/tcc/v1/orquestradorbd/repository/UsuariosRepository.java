package br.com.fatec.sp.tcc.v1.orquestradorbd.repository;

import br.com.fatec.sp.tcc.v1.orquestradorbd.model.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosModel,Long> {

    Optional<UsuariosModel> findById(Long id);
    Optional<UsuariosModel> findByNome(String nome);
    Optional<UsuariosModel> findByCpf(String cpf);

}