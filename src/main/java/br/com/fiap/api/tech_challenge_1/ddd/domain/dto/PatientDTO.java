package br.com.fiap.api.tech_challenge_1.ddd.domain.dto;

import br.com.fiap.api.tech_challenge_1.ddd.domain.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
import java.util.UUID;

public record PatientDTO(
        UUID id,
        @NotBlank(message = "Invalid name") String name,
        int age,
        Gender gender,
        @CPF(message = "Invalid CPF") String CPF,
        @Email(message = "Invalid e-mail") String email,
        String phoneNumber,
        UUID psychologistId,
        List<UUID> schedulesId
) { }
