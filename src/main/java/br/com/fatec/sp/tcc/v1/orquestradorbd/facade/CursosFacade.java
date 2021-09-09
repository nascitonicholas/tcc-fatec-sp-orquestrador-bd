package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursoRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.CursosRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.CursoResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.CursosMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.CursosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.CursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mapstruct.factory.Mappers.getMapper;

@Component
public class CursosFacade {

    @Autowired
    private CursosRepository cursoRepository;

    CursosMapper MAPPER = getMapper(CursosMapper.class);

    public List<CursoResponse> getCursos() {

        List<CursoResponse> listaResponse = new ArrayList<>();
        List<CursosModel> listCursoModel = cursoRepository.findAll();

        listCursoModel.forEach(item -> {
            CursoResponse cursoResponse = MAPPER.mapCursoModelToCursoResponse(item);
            listaResponse.add(cursoResponse);
        });

        return listaResponse;
    }


    public void postCursos(List<CursosRequestCreate> cursosRequestCreates) {

        cursosRequestCreates.forEach(item -> {
            if(cursoInexistente(item.getNome())){
                CursosModel model = MAPPER.mapCreateCursosRequestToCursosModel(item);

                cursoRepository.save(model);
            }
        });
    }

    private boolean cursoInexistente(String nome) {
        return cursoRepository.findByNome(nome).isEmpty();
    }

    public Optional<CursosModel> getCursoById(Long id) {

        return cursoRepository.findAllById(id);
    }

    public void putCursos(List<CursosRequestUpdate> cursosRequestUpdate) {

        cursosRequestUpdate.forEach(item -> {
            Optional<CursosModel> curso = getCursoById(item.getId());
            if(curso.isPresent()){
                CursosModel cursoModel = MAPPER.mapUpdateCursosRequestToCursosModel(item, curso.get());
                cursoRepository.save(cursoModel);
            }
        });
    }

    public void deleteCursos(List<CursoRequestDelete> cursoRequestDelete) {

        cursoRequestDelete.forEach(item -> {
            Optional<CursosModel> curso = getCursoById(item.getId());
            if(curso.isPresent()){
                cursoRepository.deleteById(item.getId());
            }
        });

    }
}
