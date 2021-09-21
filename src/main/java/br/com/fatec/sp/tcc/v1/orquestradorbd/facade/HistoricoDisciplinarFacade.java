package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.HistoricoDisciplinarRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.HistoricoDisciplinarRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.HistoricoDisciplinarRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.HistoricoDisciplinarResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.HistoricoDisciplinarMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.HistoricoDisciplinarModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.MateriasModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.UsuariosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.HistoricoDisciplinarRepository;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.MateriasRepository;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.UsuariosRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static br.com.fatec.sp.tcc.v1.orquestradorbd.enums.MensagensErrorEnum.*;

@Component
public class HistoricoDisciplinarFacade {

    @Autowired
    private HistoricoDisciplinarRepository historicoDisciplinarRepository;

    @Autowired
    private MateriasRepository materiasRepository;


    @Autowired
    private UsuariosRepository usuariosRepository;


    private final HistoricoDisciplinarMapper historicoDisciplinarMapper = Mappers.getMapper(HistoricoDisciplinarMapper.class);


    public List<HistoricoDisciplinarResponse> getHistoricosDisciplinares() {

        return historicoDisciplinarMapper.mapHistoricosDisciplinaresModelToHistoricosDisciplinaresResponse(historicoDisciplinarRepository.findAll());
    }

    public List<HistoricoDisciplinarResponse> getHistoricoDisciplinaresByAluno(Long idAluno) {

        Optional<UsuariosModel> aluno = usuariosRepository.findById(idAluno);

        List<HistoricoDisciplinarResponse> historicos = historicoDisciplinarMapper.mapHistoricoDisciplinarModelToHistoricoResponseByAluno(historicoDisciplinarRepository.findByAluno(aluno.get()).get());

        return historicos;
    }

    public void postHistoricoDisciplinar(HistoricoDisciplinarRequestCreate historicoDisciplinarRequestCreate) {
        try {

            historicoDisciplinarRequestCreate.getRequest().forEach(item -> {

                Optional<UsuariosModel> aluno = usuariosRepository.findById(item.getIdAluno());
                HistoricoDisciplinarModel historicoDisciplinarModel = historicoDisciplinarMapper.mapHistoricoDisciplinarRequestToHistoricoDisciplinarModel(item);
                validarUsuario(aluno, historicoDisciplinarModel);
                validarMateria(item.getIdMateria(), historicoDisciplinarModel);
                historicoDisciplinarRepository.save(historicoDisciplinarModel);

            });

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
        }
    }

    public void putHistoricoDisciplinar(HistoricoDisciplinarRequestUpdate historicoDisciplinarRequestUpdate){
        try{
            historicoDisciplinarRequestUpdate.getRequest().forEach(item -> {
                Optional<HistoricoDisciplinarModel> historico = historicoDisciplinarRepository.findById(item.getIdHistoricoDisciplinar());
                if(historico.isPresent()){
                    HistoricoDisciplinarModel historicoDisciplinarModel = historicoDisciplinarMapper.mapUpdateHistoricoDisciplinarRequestToHistoricoDisciplinarModel(item, historico.get());
                    historicoDisciplinarRepository.save(historicoDisciplinarModel);
                }
            });
        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_UPDATE.getMessage() + e);
        }
    }

    private void validarMateria(Long idMateria, HistoricoDisciplinarModel historicoDisciplinarModel) {

        Optional<MateriasModel> materia = materiasRepository.findById(idMateria);

        if (materia.isPresent()) {
            historicoDisciplinarModel.setIdMateria(materia.get());
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_materia"));
        }

    }

    public void deleteHistoricoDisciplinar(HistoricoDisciplinarRequestDelete historicoDisciplinarRequestDelete) {

        try {
            historicoDisciplinarRequestDelete.getRequest().forEach(item -> {
                historicoDisciplinarRepository.deleteById(item.getId());
            });

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_DELETE.getMessage() + e);
        }
    }


    private void validarUsuario(Optional<UsuariosModel> aluno, HistoricoDisciplinarModel historicoDisciplinarModel) {

        if (aluno.isPresent()) {
            historicoDisciplinarModel.setAluno(aluno.get());
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("id_aluno"));
        }
    }
}
