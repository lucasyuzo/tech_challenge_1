package br.com.fiap.api.tech_challenge_1.dto;

import br.com.fiap.api.tech_challenge_1.entity.Scheduling;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record PsychologistDTO(
        UUID id,
        @NotBlank(message = "Invalid name") String name,
        String CRP,
        @Email(message = "Invalid e-mail") String email,
        List<UUID> patientsId,
        List<UUID> schedulesId
) { }
