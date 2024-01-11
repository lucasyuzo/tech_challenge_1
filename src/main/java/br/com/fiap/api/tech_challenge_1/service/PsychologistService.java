package br.com.fiap.api.tech_challenge_1.service;

import java.util.UUID;

import br.com.fiap.api.tech_challenge_1.controller.expection.ControllerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.api.tech_challenge_1.dto.PsychologistDTO;
import br.com.fiap.api.tech_challenge_1.entity.Psychologist;
import br.com.fiap.api.tech_challenge_1.repository.PsychologistRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PsychologistService {

	@Autowired
	private PsychologistRepository repository;

	public PsychologistDTO findById(UUID id) {
		var psychologist = repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Psychologist not found"));
		return toPsychologistDTO(psychologist);
	}

	public PsychologistDTO save(PsychologistDTO psychologistDTO) {
		Psychologist psychologist = toPsychologist(psychologistDTO);
		psychologist = repository.save(psychologist);
		return toPsychologistDTO(psychologist);
	}
	
	public PsychologistDTO update(UUID id, PsychologistDTO psychologistDTO) {
		try {
			Psychologist psychologist = repository.getReferenceById(id);
			psychologist.setName(psychologistDTO.name());
			psychologist.setCRP(psychologistDTO.CRP());
			psychologist.setEmail(psychologistDTO.email());
			repository.save(psychologist);
			return toPsychologistDTO(psychologist);
		} catch (EntityNotFoundException exception) {
			throw new ControllerNotFoundException("Psychologist not found");
		}
	}
	
	public void delete(UUID id) {
		repository.deleteById(id);
	}

	private Psychologist toPsychologist(PsychologistDTO dto) {
		return new Psychologist(dto.id(), dto.name(), dto.CRP(), dto.email());
	}

	private PsychologistDTO toPsychologistDTO(Psychologist psychologist) {
		return new PsychologistDTO(psychologist.getId(), psychologist.getName(),
				psychologist.getCRP(), psychologist.getEmail());
	}
}
