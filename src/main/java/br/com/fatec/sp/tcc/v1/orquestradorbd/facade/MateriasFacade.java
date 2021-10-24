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
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensErrorEnum.*;

@Component
public class MateriasFacade {

    @Autowired
    private MateriasRepository materiasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    private final MateriasMapper materiasMapper = Mappers.getMapper(MateriasMapper.class);

    private MateriasModel materiasModel = new MateriasModel();


    public List<MateriasResponse> getMaterias(){

        return materiasMapper.mapMateriasModelToMateriasResponse(materiasRepository.findAll());

    }

    public MateriasResponse getById(Long id){
        try{
            return materiasMapper.mapMateriasModelToMateriasResponse(materiasRepository.findById(id).get());
        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }
    }

    public void postMaterias(MateriasRequestCreate materiasRequestCreate){
        try{
            materiasRequestCreate.getRequest().forEach(item -> {
                this.materiasModel = materiasMapper.mapCreateMateriaRequestToMateriasModel(item);
                validarProfessores(item.getIdProfessores());
                materiasRepository.saveAndFlush(materiasModel);
            });
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
        }
    }

    public void putMaterias(MateriasRequestUpdate materiasRequestUpdate){
        try{

            materiasRequestUpdate.getRequest().forEach(item -> {

                Optional<MateriasModel> materia = materiasRepository.findById(item.getId());
                if(materia.isPresent()){
                    MateriasModel materiasModel = materiasMapper.mapUpdateMateriasRequestToMateriasModel(item, materia.get());
                    validarProfessores(item.getIdProfessores());
                    materiasRepository.save(materiasModel);
                }

            });

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_UPDATE.getMessage() + e);
        }
    }


    public void deleteMaterias(MateriasRequestDelete materiasRequestDelete){
        try{

            materiasRequestDelete.getRequest().forEach(item ->{
                materiasRepository.deleteById(item.getId());
            });

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_DELETE.getMessage() + e);
        }
    }

    private void validarProfessores(List<Long> idProfessores) {

        idProfessores.forEach(item ->{
            Optional<UsuariosModel> professorById = usuariosRepository.findByNrMatricula(item);
            if(professorById.isPresent()){
                this.materiasModel.addProfessores(professorById.get());
            }else{
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("ids_professores"));
            }
        });

    }


}
