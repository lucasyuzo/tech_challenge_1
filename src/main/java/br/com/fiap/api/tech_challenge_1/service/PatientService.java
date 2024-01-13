package br.com.fiap.api.tech_challenge_1.service;

import br.com.fiap.api.tech_challenge_1.controller.expection.ControllerNotFoundException;
import br.com.fiap.api.tech_challenge_1.dto.PatientDTO;
import br.com.fiap.api.tech_challenge_1.entity.Patient;
import br.com.fiap.api.tech_challenge_1.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public PatientDTO findById(UUID id) {
        var patient = repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Patient not found"));
        return toPatientDTO(patient);
    }

    public Page<PatientDTO> findAll(Pageable pageable) {
        Page<Patient> patients = repository.findAll(pageable);
        return patients.map(this::toPatientDTO);
    }

    public PatientDTO save(PatientDTO patientDTO) {
        Patient patient = toPatient(patientDTO);
        patient = repository.save(patient);
        return toPatientDTO(patient);
    }

    public PatientDTO update(UUID id, PatientDTO patientDTO) {
        try {
            Patient patient = repository.getReferenceById(id);
            patient.setName(patientDTO.name());
            patient.setAge(patientDTO.age());
            patient.setGender(patientDTO.gender());
            patient.setCPF(patientDTO.CPF());
            patient.setEmail(patientDTO.email());
            patient.setPhoneNumber(patientDTO.phoneNumber());
            patient = repository.save(patient);
            return toPatientDTO(patient);
        } catch (EntityNotFoundException exception) {
            throw new ControllerNotFoundException("Patient not found");
        }
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    private Patient toPatient(PatientDTO patientDTO) {
        return new Patient(
                patientDTO.id(),
                patientDTO.name(),
                patientDTO.age(),
                patientDTO.gender(),
                patientDTO.CPF(),
                patientDTO.email(),
                patientDTO.phoneNumber());
    }

    private PatientDTO toPatientDTO(Patient patient) {
        return new PatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getGender(),
                patient.getCPF(),
                patient.getEmail(),
                patient.getPhoneNumber()
        );
    }
}
