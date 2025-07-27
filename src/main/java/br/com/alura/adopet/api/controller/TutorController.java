package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.TutorAtualizacaoDTO;
import br.com.alura.adopet.api.dto.TutorCadastroDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid TutorCadastroDTO dto) {
        try {
            tutorService.cadastrar(dto);
            return ResponseEntity.ok("Tutor cadastrado com sucesso!");
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid TutorAtualizacaoDTO dto) {
        try{
            tutorService.atualizar(dto);
            return ResponseEntity.ok("Tutor atualizado com sucesso!");
        } catch (ValidationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
