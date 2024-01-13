package br.com.fiap.api.tech_challenge_1.entity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "tb_psychologist")
public class Psychologist {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String CRP;
	private String email;
	private List<UUID> patientsId;

	public Psychologist() {}

	public Psychologist(UUID id, String name, String CRP, String email, List<UUID> patientsId) {
		this.id = id;
		this.name = name;
		this.CRP = CRP;
		this.email = email;
		this.patientsId = patientsId;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCRP() {
		return CRP;
	}

	public void setCRP(String CRP) {
		this.CRP = CRP;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UUID> getPatientsId() {
		return this.patientsId;
	}

	public void addPatient(UUID patient) {
		this.patientsId.add(patient);
	}

	public void removePatient(UUID id) {
		this.patientsId.remove(id);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Psychologist psychologist = (Psychologist) o;
		return Objects.equals(id, psychologist.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Psychologist{" +
				"name='" + name + '\'' +
				", CRP='" + CRP + '\'' +
				'}';
	}
}
