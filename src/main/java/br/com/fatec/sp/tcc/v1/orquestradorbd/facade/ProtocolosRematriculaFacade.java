package br.com.fatec.sp.tcc.v1.orquestradorbd.facade;

import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.ProtocolosRematriculaRequestCreate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.ProtocolosRematriculaRequestDelete;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.request.ProtocolosRematriculaRequestUpdate;
import br.com.fatec.sp.tcc.v1.orquestradorbd.controller.response.ProtocolosRematriculaResponse;
import br.com.fatec.sp.tcc.v1.orquestradorbd.mapper.ProtocolosRematriculasMapper;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.MateriasModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.ProtocolosRematriculaModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.model.UsuariosModel;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.MateriasRepository;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.ProtocolosRematriculaRepository;
import br.com.fatec.sp.tcc.v1.orquestradorbd.repository.UsuariosRepository;
import br.com.fatec.sp.tcc.v1.orquestradorbd.utils.Utils;
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
public class ProtocolosRematriculaFacade {

    @Autowired
    private ProtocolosRematriculaRepository protocolosRematriculaRepository;

    @Autowired
    private MateriasRepository materiasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    private final ProtocolosRematriculasMapper protocolosRematriculasMapper = Mappers.getMapper(ProtocolosRematriculasMapper.class);

    public List<ProtocolosRematriculaResponse> getProtocolosRematricula() {

        return protocolosRematriculasMapper.mapProtocolosRematriculasModelToProtocolosRematriculasResponse(protocolosRematriculaRepository.findAll());
    }

    public List<ProtocolosRematriculaResponse> getProtocolosRematriculasByUsuario(Long idAluno){

        try{

            return protocolosRematriculasMapper.mapProtocolosRematriculasModelToProtocolosRematriculasResponse(protocolosRematriculaRepository.findByAluno(usuariosRepository.findById(idAluno).get()).get());

        }catch (Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }

    }

    public ProtocolosRematriculaResponse getProtocolosRematriculaById(Long id) {
        try {

            return protocolosRematriculasMapper.mapProtocoloRematriculaModelToProtocoloRematriculaResponse(protocolosRematriculaRepository.findById(id).get());

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_FIND.getMessage() + e);
        }
    }

    public void postProtocolosRematricula(ProtocolosRematriculaRequestCreate protocolosRematriculaRequestCreate) {

        try {
            protocolosRematriculaRequestCreate.getRequest().forEach(item -> {

                ProtocolosRematriculaModel protocolosRematriculaModel = protocolosRematriculasMapper.mapCreateProtocoloRematriculaRequestToProtocoloRematriculaModel(item);
                getCodProtocolo(protocolosRematriculaModel);
                validarMateria(item.getIdsMateria(), protocolosRematriculaModel);
                validarUsuario(item.getNrMatriculaUsuario(), protocolosRematriculaModel);
                protocolosRematriculaRepository.save(protocolosRematriculaModel);
            });

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_CREATE.getMessage() + e);
        }
    }

    private void getCodProtocolo(ProtocolosRematriculaModel protocolosRematriculaModel) {
        String codigoProtocolo;
        Optional<ProtocolosRematriculaModel> byCodProtrocolo;
        do {
            codigoProtocolo = Utils.getStringAleatoria();
            byCodProtrocolo = protocolosRematriculaRepository.findByCodProtrocolo(codigoProtocolo);
        } while (byCodProtrocolo.isPresent());
        protocolosRematriculaModel.setCodProtrocolo(codigoProtocolo);
    }

    public void putProtolocosRematriculas(ProtocolosRematriculaRequestUpdate protocolosRematriculaRequestUpdate) {
        try {
            protocolosRematriculaRequestUpdate.getRequest().forEach(item -> {
                Optional<ProtocolosRematriculaModel> protocolo = protocolosRematriculaRepository.findById(item.getId());
                if (protocolo.isPresent()) {
                    ProtocolosRematriculaModel protocolosRematriculaModel = protocolosRematriculasMapper.mapUpdateProtocoloRematriculaRequestToProtocoloRematriculaModel(item, protocolo.get());
                    validarMateria(item.getIdMateria(), protocolosRematriculaModel);
                    validarUsuario(item.getNrMatriculaUsuario(), protocolosRematriculaModel);
                    protocolosRematriculaRepository.save(protocolosRematriculaModel);
                }
            });


        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_UPDATE.getMessage() + e);
        }
    }


    public void deleteProcolosRematriculas(ProtocolosRematriculaRequestDelete protocolosRematriculaRequestDelete) {
        try {
            protocolosRematriculaRequestDelete.getRequest().forEach(item -> {
                protocolosRematriculaRepository.deleteById(item.getId());
            });

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_DELETE.getMessage() + e);
        }
    }


    private void validarUsuario(Long nrMatriculaUsuario, ProtocolosRematriculaModel protocolosRematriculaModel) {

        Optional<UsuariosModel> usuarioById = usuariosRepository.findByNrMatricula(nrMatriculaUsuario);

        if (usuarioById.isPresent()) {
            protocolosRematriculaModel.setAluno(usuarioById.get());
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("nr_matricula_usuario"));
        }
    }

    private void validarMateria(List<Long> listIdMateria, ProtocolosRematriculaModel protocolosRematriculaModel) {

        List<MateriasModel> result = new ArrayList<>();

        listIdMateria.forEach(item -> {
            Optional<MateriasModel> materiaById = materiasRepository.findById(item);
            if (materiaById.isPresent()) {
                result.add(materiaById.get());
            } else {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE_ERROR_FOREING_KEY.messageErroFk("ids_materia"));
            }

        });

        protocolosRematriculaModel.setMaterias(result);

    }
}
