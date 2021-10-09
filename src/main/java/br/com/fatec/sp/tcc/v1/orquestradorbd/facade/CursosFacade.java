package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursoRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.CursoCreateUpdateResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.CursoResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.CursosMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.CursosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.CursosRepository;
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
public class CursosFacade {

    @Autowired
    private CursosRepository cursoRepository;

    private final CursosMapper cursosMapper = Mappers.getMapper(CursosMapper.class);


    public List<CursoResponse> getCursos() {

        List<CursosModel> listCurso = cursoRepository.findAll();

        return cursosMapper.mapCursosModelToCursosResponse(listCurso);
    }

    public CursoResponse getCursoById(Long id) {

        try {
            CursosModel curso = cursoRepository.findById(id).get();
            return cursosMapper.mapCursoModelToCursoResponse(curso);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }
    }

    public List<CursoCreateUpdateResponse> postCursos(CursosRequestCreate cursosRequestCreates) {

        List<CursoCreateUpdateResponse> response = new ArrayList<>();

        try {
            cursosRequestCreates.getRequest().forEach(item -> {
                if (cursoInexistente(item.getNome())) {
                    CursosModel model = cursosMapper.mapCreateCursosRequestToCursosModel(item);

                    CursosModel cursoModel = cursoRepository.save(model);
                    response.add(cursosMapper.mapIdCursoModelToIdCursoResponse(cursoModel.getId()));
                }
            });

            return response;

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
        }

    }

    private boolean cursoInexistente(String nome) {
        return cursoRepository.findByNome(nome).isEmpty();
    }



    public  List<CursoCreateUpdateResponse> putCursos(CursosRequestUpdate cursosRequestUpdate) {

        List<CursoCreateUpdateResponse> response = new ArrayList<>();

        try{
            cursosRequestUpdate.getRequest().forEach(item -> {
                Optional<CursosModel> curso = cursoRepository.findById(item.getId());
                if(curso.isPresent()){
                    CursosModel cursoModel = cursosMapper.mapUpdateCursosRequestToCursosModel(item, curso.get());
                    cursoRepository.save(cursoModel);
                    response.add(cursosMapper.mapIdCursoModelToIdCursoResponse(cursoModel.getId()));
                }

            });

            return response;

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_UPDATE.getMessage() + e);
        }
    }

    public void deleteCursos(CursoRequestDelete cursoRequestDelete) {

        try{
            cursoRequestDelete.getRequestDelete().forEach(item -> {
                Optional<CursosModel> curso = cursoRepository.findById(item.getId());
                if(curso.isPresent()){
                    cursoRepository.deleteById(item.getId());
                }
            });
        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_DELETE.getMessage() + e);
        }

    }

}
