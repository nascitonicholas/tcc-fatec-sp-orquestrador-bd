package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.MateriasRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.MateriasRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.MateriasRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.MateriasResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.MateriasMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.MateriasModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.UsuariosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.MateriasRepository;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.UsuariosRepository;
import br.com.fatec.sp.tcc.v1.orquestradorbd.service.MateriaService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensErrorEnum.*;

@Component
public class MateriasFacade {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private MateriaService materiaService;

    private final MateriasMapper materiasMapper = Mappers.getMapper(MateriasMapper.class);

    private MateriasModel materiasModel = new MateriasModel();


    public List<MateriasResponse> getMaterias(){
        final List<MateriasModel> listaMaterias = materiaService.findAll();
        return materiasMapper.mapMateriasModelToMateriasResponse(listaMaterias);

    }

    public MateriasResponse getById(final Long id){
        try{
            MateriasModel materia = materiaService.findById(id);
            if (Objects.nonNull(materia)) {
                return materiasMapper.mapMateriasModelToMateriasResponse(materia);
            }
            return new MateriasResponse();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }
    }

    public void postMaterias(final MateriasRequestCreate materiasRequestCreate){
        try{
            if (!materiasRequestCreate.getRequest().isEmpty()){
                materiasRequestCreate.getRequest().forEach(item -> {
                    this.materiasModel = mapToMateriaModel(item);
                    if (Objects.nonNull(this.materiasModel)) {
                        validarProfessores(item);
                        materiaService.saveAndFlush(this.materiasModel);
                    }
                });
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
        }
    }

    public void putMaterias(MateriasRequestUpdate materiasRequestUpdate){
        try{
            if(Objects.nonNull(materiasRequestUpdate)) {
                if (!materiasRequestUpdate.getRequest().isEmpty()) {
                    materiasRequestUpdate.getRequest().forEach(item -> {
                        MateriasModel materia = materiaService.findById(item.getId());
                        if(Objects.nonNull(materia)){
                            MateriasModel materiasModel = materiasMapper.mapUpdateMateriasRequestToMateriasModel(item, materia);
                            if(!item.getIdProfessores().isEmpty()) validarProfessores(item.getIdProfessores());
                            materiaService.saveAndFlush(materiasModel);
                        }
                    });
                }
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_UPDATE.getMessage() + e);
        }
    }


    public void deleteMaterias(MateriasRequestDelete materiasRequestDelete){
        try{
            if(Objects.nonNull(materiasRequestDelete)) {
                if(!materiasRequestDelete.getRequest().isEmpty()) {
                    materiasRequestDelete.getRequest().forEach(item ->{
                        materiaService.deleteById(item);
                    });
                }
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_DELETE.getMessage() + e);
        }
    }

    private void validarProfessores(final MateriasRequestCreate.MateriaCreate materia) {
        if (Objects.nonNull(materia)) {
            if (!materia.getIdProfessores().isEmpty()) {
                materia.getIdProfessores().forEach(item ->{
                    Optional<UsuariosModel> professorById = usuariosRepository.findByNrMatricula(item);
                    if(professorById.isPresent()){
                        this.materiasModel.getProfessores().add(professorById.get());
                    }
                });
            }
        }
    }

    private void validarProfessores(final List<Long> idProfessores) {
        if (!idProfessores.isEmpty()) {
            idProfessores.forEach(item ->{
                Optional<UsuariosModel> professorById = usuariosRepository.findByNrMatricula(item);
                if(professorById.isPresent()){
                    this.materiasModel.getProfessores().add(professorById.get());
                }
            });
        }
    }

    public MateriasModel mapToMateriaModel(final MateriasRequestCreate.MateriaCreate materia) {
        try {
            if (Objects.nonNull(materia)) {
                return materiasMapper.mapCreateMateriaRequestToMateriasModel(materia);
            }
            return null;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível mapear para materia model");
        }
    }

}
