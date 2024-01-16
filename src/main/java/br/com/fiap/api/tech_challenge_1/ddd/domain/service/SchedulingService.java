package br.com.fiap.api.tech_challenge_1.ddd.domain.service;

import br.com.fiap.api.tech_challenge_1.ddd.application.controller.expection.ControllerNotFoundException;
import br.com.fiap.api.tech_challenge_1.ddd.application.controller.expection.SchedulingNotValidException;
import br.com.fiap.api.tech_challenge_1.ddd.domain.dto.SchedulingDTO;
import br.com.fiap.api.tech_challenge_1.ddd.domain.entity.Patient;
import br.com.fiap.api.tech_challenge_1.ddd.domain.entity.Psychologist;
import br.com.fiap.api.tech_challenge_1.ddd.domain.entity.Scheduling;
import br.com.fiap.api.tech_challenge_1.ddd.infrastructure.repository.PatientRepository;
import br.com.fiap.api.tech_challenge_1.ddd.infrastructure.repository.PsychologistRepository;
import br.com.fiap.api.tech_challenge_1.ddd.infrastructure.repository.SchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SchedulingService {

    @Autowired
    private SchedulingRepository schedulingRepository;
    @Autowired
    private PsychologistRepository psychologistRepository;
    @Autowired
    private PatientRepository patientRepository;

    public List<SchedulingDTO> getSchedulesByPsychologistId(UUID id) {
        Psychologist psychologist = psychologistRepository
                .findById(id)
                .orElseThrow(() -> new ControllerNotFoundException("Psychologist not found"));
        List<SchedulingDTO> schedulesDTO = new ArrayList<>();
        for (UUID schedulingId : psychologist.getSchedulesId()) {
            Scheduling scheduling = schedulingRepository
                    .findById(schedulingId)
                    .orElseThrow(() -> new ControllerNotFoundException("Scheduling not found"));
            schedulesDTO.add(this.toSchedulingDTO(scheduling));
        }
        return schedulesDTO;
    }

    public List<SchedulingDTO> getSchedulesByPatientId(UUID id) {
        Patient patient = patientRepository
                .findById(id)
                .orElseThrow(() -> new ControllerNotFoundException("Patient not found"));
        List<SchedulingDTO> schedulesDTO = new ArrayList<>();
        for (UUID schedulingId : patient.getSchedulesId()) {
            Scheduling scheduling = schedulingRepository
                    .findById(schedulingId)
                    .orElseThrow(() -> new ControllerNotFoundException("Scheduling not found"));
            schedulesDTO.add(this.toSchedulingDTO(scheduling));
        }
        return schedulesDTO;
    }

    public SchedulingDTO schedule(SchedulingDTO schedulingDTO) {
        Psychologist psychologist = psychologistRepository
                .findById(schedulingDTO.psychologistId())
                .orElseThrow(() -> new ControllerNotFoundException("Psychologist not found"));
        Patient patient = patientRepository
                .findById(schedulingDTO.patientId())
                .orElseThrow(() -> new ControllerNotFoundException("Patient not found"));
        Scheduling scheduling = toScheduling(schedulingDTO);
        if (this.validateScheduling(scheduling, psychologist, patient)) {
            scheduling = schedulingRepository.save(scheduling);
            psychologist.addSchedule(scheduling.getId());
            psychologistRepository.save(psychologist);
            patient.addSchedule(scheduling.getId());
            patientRepository.save(patient);
            return toSchedulingDTO(scheduling);
        } else {
            throw new SchedulingNotValidException("Schedule already exist");
        }
    }

    private Scheduling toScheduling(SchedulingDTO schedulingDTO) {
        LocalDateTime date = LocalDateTime.of(
                schedulingDTO.year(),
                schedulingDTO.month(),
                schedulingDTO.day(),
                schedulingDTO.hour(),
                schedulingDTO.minute()
        );
        return new Scheduling(
                schedulingDTO.id(),
                schedulingDTO.psychologistId(),
                schedulingDTO.patientId(),
                date
        );
    }

    private SchedulingDTO toSchedulingDTO(Scheduling scheduling) {
        return new SchedulingDTO(
                scheduling.getId(),
                scheduling.getPsychologistId(),
                scheduling.getPatientId(),
                scheduling.getYear(),
                scheduling.getMonth(),
                scheduling.getDay(),
                scheduling.getHour(),
                scheduling.getMinute()
        );
    }

    private boolean validateScheduling(Scheduling newScheduling, Psychologist psychologist, Patient patient) {
        for (UUID schedulingId : psychologist.getSchedulesId()) {
            Scheduling scheduling = schedulingRepository.getReferenceById(schedulingId);
            LocalDateTime schedulingDate = scheduling.getDate();
            LocalDateTime newSchedulingDate = newScheduling.getDate();
            if (schedulingDate == newSchedulingDate) {
                return false;
            } else if (
                    schedulingDate.getYear() == newSchedulingDate.getYear() &&
                    schedulingDate.getMonth() == newSchedulingDate.getMonth() &&
                    schedulingDate.getDayOfMonth() == newSchedulingDate.getDayOfMonth() &&
                    (schedulingDate.getHour() == newSchedulingDate.getHour() || schedulingDate.getHour() + 1 == newSchedulingDate.getHour())
            ) {
                return false;
            }
        }
        for (UUID schedulingId : patient.getSchedulesId()) {
            Scheduling scheduling = schedulingRepository.getReferenceById(schedulingId);
            if (scheduling.getDate() == newScheduling.getDate()) {
                return false;
            }
        }
        return true;
    }
}
