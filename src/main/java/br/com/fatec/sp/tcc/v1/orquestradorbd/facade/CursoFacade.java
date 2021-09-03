package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.CursoResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.CursoMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.CursosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CursoFacade {

    @Autowired
    private CursoRepository cursoRepository;


    public List<CursoResponse> getCursos() {

        List<CursoResponse> listaResponse = new ArrayList<>();
        List<CursosModel> listCursoModel = cursoRepository.findAll();

        listCursoModel.stream().forEach(item -> {
            CursoResponse cursoResponse = CursoMapper.MAPPER.mapCursoModelToCursoResponse(item);
            listaResponse.add(cursoResponse);
        });

        return listaResponse;
    }


}
