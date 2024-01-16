package br.com.fiap.api.tech_challenge_1.ddd.domain.entity;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

@Entity
@Table(name = "tb_scheduling")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID psychologistId;
    private UUID patientId;
    private LocalDateTime date;

    public Scheduling() { }

    public Scheduling(UUID id, UUID psychologistId, UUID patientId, LocalDateTime date) {
        this.id = id;
        this.psychologistId = psychologistId;
        this.patientId = patientId;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPsychologistId() {
        return psychologistId;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getYear() {
        return this.date.getYear();
    }

    public Month getMonth() {
        return this.date.getMonth();
    }

    public int getDay() {
        return this.date.getDayOfMonth();
    }

    public DayOfWeek getDayOfWeek() {
        return this.date.getDayOfWeek();
    }

    public int getHour() {
        return this.date.getHour();
    }

    public int getMinute() {
        return this.date.getMinute();
    }
}
