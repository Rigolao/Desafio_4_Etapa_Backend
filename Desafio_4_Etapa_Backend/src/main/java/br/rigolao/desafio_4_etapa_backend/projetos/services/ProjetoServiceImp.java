package br.rigolao.desafio_4_etapa_backend.projetos.services;

import br.rigolao.desafio_4_etapa_backend.exceptions.ProjetoNaoEncontradoException;
import br.rigolao.desafio_4_etapa_backend.exceptions.SemPermissaoException;
import br.rigolao.desafio_4_etapa_backend.exceptions.UsuarioNaoEncontradoException;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.ProjetoModel;
import br.rigolao.desafio_4_etapa_backend.projetos.repositories.ProjetosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImp implements ProjetosService{

    private ProjetosRepository projetosRepository;

    @Autowired
    public ProjetoServiceImp(ProjetosRepository projetosRepository) {
        this.projetosRepository = projetosRepository;
    }


    @Override
    public List<ProjetoModel> findAllProjects() {
        Optional<List<ProjetoModel>> listaProjetos = projetosRepository.findAllByPublico(true);
        return listaProjetos.orElse(Collections.emptyList());
    }

    @Override
    public void saveProjeto(ProjetoModel projeto) {
        projetosRepository.save(projeto);
    }

    @Override
    public List<ProjetoModel> retornarMeusProjetos(CientistaModel cientista) {
        Optional<List<ProjetoModel>> listaProjetos = projetosRepository.findAllByCientista(cientista);
        return listaProjetos.orElse(Collections.emptyList());
    }

    @Override
    public ProjetoModel retornaMeuProjeto(Integer id, CientistaModel cientista) {
        Optional<ProjetoModel> projetoModel = projetosRepository.findProjetoModelByIdAndCientista(id, cientista);

        if(projetoModel.isPresent()){
            if (!projetoModel.get().getCientista().getCpf().equals(cientista.getCpf())){
                throw new SemPermissaoException("Esse projeto não pode ser editado por você!");
            }
            return projetoModel.get();
        } else {
            throw new ProjetoNaoEncontradoException();
        }
    }
}
