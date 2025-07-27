package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AbrigoDTO(@NotBlank String nome, @NotBlank String numero, @NotBlank String email) {}
