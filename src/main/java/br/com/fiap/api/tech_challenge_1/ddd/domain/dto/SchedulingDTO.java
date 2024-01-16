package br.com.fiap.api.tech_challenge_1.ddd.domain.dto;

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
