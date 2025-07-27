package br.com.alura.adopet.api.service;


import br.com.alura.adopet.api.dto.DadosDetalhesPet;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PetService {

    @Autowired
    private PetRepository repository;

    public List<DadosDetalhesPet> ListarOsPetsDisponiveis(){
        List<Pet> pets = repository.findAll();
        List<DadosDetalhesPet> disponiveis = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getAdotado() == false) {
                disponiveis.add(new DadosDetalhesPet(pet));
            }
        }
        return disponiveis;
    }
}
