package br.com.fiap.api.tech_challenge_1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PsychologistDTO(
        UUID id,
        @NotBlank(message = "Invalid name") String name,
        String CRM,
        @Email(message = "Invalid e-mail") String email) {
}
