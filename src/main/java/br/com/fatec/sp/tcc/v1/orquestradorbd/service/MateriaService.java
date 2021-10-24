package br.com.fatec.sp.tcc.v1.orquestradorbd.service;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.MateriasRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.MateriasModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.MateriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriasRepository materiasRepository;

    public void saveAndFlush(final MateriasModel materiasModel) {
        try {
            materiasRepository.saveAndFlush(materiasModel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível salvar a matéria.");
        }
    }

    public List<MateriasModel> findAll () {
        try {
            return materiasRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível buscar todas as matérias.");
        }
    }

    public MateriasModel findById(final Long id) {
        try {
            Optional<MateriasModel> materia = materiasRepository.findById(id);
            if (materia.isPresent()) {
                return materia.get();
            }
            return null;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível salvar a matéria.");
        }
    }

    public void deleteById(final MateriasRequestDelete.MateriasDelete materiasDeletes) {
        try {
            if(Objects.nonNull(materiasDeletes)) {
                if(Objects.nonNull(materiasDeletes.getId())) {
                    materiasRepository.deleteById(materiasDeletes.getId());
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível salvar a matéria.");
        }
    }

}
