package br.com.fiap.api.tech_challenge_1.controller;

import java.util.UUID;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.api.tech_challenge_1.dto.PsychologistDTO;
import br.com.fiap.api.tech_challenge_1.service.PsychologistService;

@RestController
@RequestMapping("/psychologist")
public class PsychologistController {

	@Autowired
	private PsychologistService service;

	@GetMapping("/{id}")
	public ResponseEntity<PsychologistDTO> findById(@PathVariable(name = "id", required = true) UUID id) {
		var psychologistDTO = service.findById(id);
		return ResponseEntity.ok(psychologistDTO);
	}

	@PostMapping
	public ResponseEntity<PsychologistDTO> save(@Valid @RequestBody PsychologistDTO psychologistDTO) {
		psychologistDTO = service.save(psychologistDTO);
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(psychologistDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PsychologistDTO> update(
			@PathVariable(name = "id", required = true) UUID id,
			@Valid @RequestBody PsychologistDTO psychologistDTO
	) {
		PsychologistDTO updatedPsychologistDTO = service.update(id, psychologistDTO);
		return ResponseEntity.ok(updatedPsychologistDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id", required = true) UUID id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
