package br.com.alura.adopet.api.validacoes.abrigo;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidarTelefoneDuplicado implements ValidadorCadastroAbrigo {
    @Autowired
    private AbrigoRepository repository;

    @Override
    public void validar(AbrigoDTO dto) {
        if (repository.existsByTelefone(dto.telefone())) {
            throw new ValidacaoException("Telefone já cadastrado para outro abrigo!");
        }
    }
}
