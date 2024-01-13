package br.com.fiap.api.tech_challenge_1.repository;

import br.com.fiap.api.tech_challenge_1.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> { }
