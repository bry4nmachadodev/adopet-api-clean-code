package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.TutorAtualizacaoDTO;
import br.com.alura.adopet.api.dto.TutorCadastroDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public void cadastrar(TutorCadastroDTO dto){
        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.telefone());
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());

        if (telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        }

        Tutor tutor = new Tutor(dto.nome(), dto.email(), dto.telefone());
        repository.save(tutor);
    }

    public void atualizar(TutorAtualizacaoDTO dto) {
        Tutor tutor = repository.getReferenceById(dto.id());
        tutor.setNome(dto.nome());
        tutor.setEmail(dto.email());
        tutor.setTelefone(dto.telefone());
        repository.save(tutor);
    }

}
