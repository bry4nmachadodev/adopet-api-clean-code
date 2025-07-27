package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TutorAtualizacaoDTO(@NotNull Long id, @NotBlank String nome, @NotBlank String email, @NotBlank String telefone) {}
