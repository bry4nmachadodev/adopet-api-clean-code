package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validacoes.abrigo.ValidadorCadastroAbrigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private List<ValidadorCadastroAbrigo> validacoes;

    public List<Abrigo> listarTodos() {
        return repository.findAll();
    }

    public void cadastarAbrigo(AbrigoDTO dto){
        validacoes.forEach(v -> v.validar(dto));

        //criacao da entidade abrigo de forma segura
        Abrigo abrigo = new Abrigo(dto.nome(), dto.telefone(), dto.email());

        repository.save(abrigo);
    }

    public List<Pet> listarPets(String idOuNome) {
        try {
            Long id = Long.parseLong(idOuNome);
            return repository.getReferenceById(id).getPets();
        } catch (NumberFormatException e) {
            return repository.findByNome(idOuNome).getPets();
        }
    }


    public void cadastrarPet(String idOuNome, PetDTO dto){
        try {
            Long id = Long.parseLong(idOuNome);
            Abrigo abrigo = repository.getReferenceById(id);

            Pet pet = new Pet(dto.nome(), dto.tipo(), dto.raca(), abrigo);
            abrigo.getPets().add(pet);
            repository.save(abrigo);
        } catch (NumberFormatException nfe) {
            Abrigo abrigo = repository.findByNome(idOuNome);

            Pet pet = new Pet(dto.nome(), dto.tipo(), dto.raca(), abrigo);
            abrigo.getPets().add(pet);
            repository.save(abrigo);
        }
    }
}
