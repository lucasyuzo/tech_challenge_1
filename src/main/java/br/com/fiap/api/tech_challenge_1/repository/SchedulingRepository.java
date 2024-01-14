package br.com.fiap.api.tech_challenge_1.repository;

import br.com.fiap.api.tech_challenge_1.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> { }
