package br.com.fiap.api.tech_challenge_1.ddd.application.controller;

import br.com.fiap.api.tech_challenge_1.ddd.domain.dto.PatientDTO;
import br.com.fiap.api.tech_challenge_1.ddd.domain.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable(name = "id", required = true) UUID id) {
        PatientDTO patientDTO = service.findById(id);
        return ResponseEntity.ok(patientDTO);
    }

    @GetMapping
    public  ResponseEntity<Page<PatientDTO>> findAll(@PageableDefault(size = 10, page = 0, sort = "name") Pageable pageable) {
        Page<PatientDTO> patientDTOS = service.findAll(pageable);
        return ResponseEntity.ok(patientDTOS);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> save(@Valid @RequestBody PatientDTO patientDTO) {
        patientDTO = service.save(patientDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(patientDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(
            @PathVariable(name = "id", required = true) UUID id,
            @Valid @RequestBody PatientDTO patientDTO
    ) {
        PatientDTO updatedPatientDTO = service.update(id, patientDTO);
        return ResponseEntity.ok(updatedPatientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientDTO> deleteById(@PathVariable(name = "id", required = true) UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
