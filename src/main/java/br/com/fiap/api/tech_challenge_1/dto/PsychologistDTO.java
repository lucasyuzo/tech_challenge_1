package br.com.fiap.api.tech_challenge_1.dto;

import java.util.UUID;

public record PsychologistDTO(UUID id, String name, String CRM, String email) {
}
