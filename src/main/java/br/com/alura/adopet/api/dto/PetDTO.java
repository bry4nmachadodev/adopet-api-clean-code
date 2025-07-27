package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.TipoPet;
import jakarta.validation.constraints.NotBlank;

public record PetDTO(@NotBlank String nome, @NotBlank TipoPet tipo, @NotBlank String raca) {}

