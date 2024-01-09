package br.com.fiap.api.tech_challenge_1.service;

import java.util.UUID;

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
		var psychologist = repository.findById(id).orElseThrow(null);
		return toPsychologistDTO(psychologist);
	}

	public PsychologistDTO save(PsychologistDTO pychologistDTO) {
		Psychologist psychologist = toPsychologist(pychologistDTO);
		psychologist = repository.save(psychologist);
		return toPsychologistDTO(psychologist);
	}
	
	public PsychologistDTO update(UUID id, PsychologistDTO pychologistDTO) {
		try {
			Psychologist psychologist = repository.getReferenceById(id);
			psychologist.setName(pychologistDTO.name());
			psychologist.setCRM(pychologistDTO.CRM());
			psychologist.setEmail(pychologistDTO.email());
			repository.save(psychologist);
			return toPsychologistDTO(psychologist);
		} catch (EntityNotFoundException exception) {
			throw exception;
		}
	}
	
	public void delete(UUID id) {
		repository.deleteById(id);
	}

	private Psychologist toPsychologist(PsychologistDTO dto) {
		return new Psychologist(dto.id(), dto.name(), dto.CRM(), dto.email());
	}

	private PsychologistDTO toPsychologistDTO(Psychologist psychologist) {
		return new PsychologistDTO(psychologist.getId(), psychologist.getName(),
				psychologist.getCRM(), psychologist.getEmail());
	}
}
