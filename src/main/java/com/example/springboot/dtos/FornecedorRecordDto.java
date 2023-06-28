package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FornecedorRecordDto(@NotBlank String razaoSocial, @NotBlank String nomeFantasia, @NotBlank String cnpj,
		@NotBlank String endereco, @NotBlank String fone, @NotBlank String email, @NotNull int inscricaoEstadual) {
}