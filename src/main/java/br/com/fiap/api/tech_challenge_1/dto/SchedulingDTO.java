package br.com.fiap.api.tech_challenge_1.dto;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

public record SchedulingDTO(
        UUID id,
        UUID psychologistId,
        UUID patientId,
        int year,
        Month month,
        int day,
        int hour,
        int minute
) { }
