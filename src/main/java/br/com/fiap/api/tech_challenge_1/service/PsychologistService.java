package br.com.fiap.api.tech_challenge_1.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.api.tech_challenge_1.dto.PsychologistDTO;
import br.com.fiap.api.tech_challenge_1.entity.Psychologist;
import br.com.fiap.api.tech_challenge_1.repository.PsychologistRepository;

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

	private Psychologist toPsychologist(PsychologistDTO dto) {
		return new Psychologist(dto.id(), dto.name(), dto.CRM(), dto.email());
	}

	private PsychologistDTO toPsychologistDTO(Psychologist psychologist) {
		return new PsychologistDTO(psychologist.getId(), psychologist.getName(),
				psychologist.getCRM(), psychologist.getEmail());
	}
}
