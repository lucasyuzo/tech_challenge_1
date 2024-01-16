package br.com.fiap.api.tech_challenge_1.ddd.application.controller;

import br.com.fiap.api.tech_challenge_1.ddd.domain.dto.SchedulingDTO;
import br.com.fiap.api.tech_challenge_1.ddd.domain.aggregate.SchedulingAggregate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    @Autowired
    SchedulingAggregate service;

    @GetMapping("/psychologist/{id}")
    public ResponseEntity<List<SchedulingDTO>> getPsychologistSchedules(@PathVariable(name = "id", required = true) UUID id) {
        List<SchedulingDTO> schedulesDTO = service.getSchedulesByPsychologistId(id);
        return ResponseEntity.ok(schedulesDTO);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<SchedulingDTO>> getPatientSchedules(@PathVariable(name = "id", required = true) UUID id) {
        List<SchedulingDTO> schedulesDTO = service.getSchedulesByPatientId(id);
        return ResponseEntity.ok(schedulesDTO);
    }

    @PostMapping
    public ResponseEntity<SchedulingDTO> scheduling(@Valid @RequestBody SchedulingDTO schedulingDTO) {
        schedulingDTO = service.schedule(schedulingDTO);
        return ResponseEntity.ok(schedulingDTO);
    }
}
