package br.com.fiap.api.tech_challenge_1.ddd.infrastructure.repository;

import org.springframework.stereotype.Repository;

import br.com.fiap.api.tech_challenge_1.ddd.domain.entity.Psychologist;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, UUID> { }
