package br.com.fiap.api.tech_challenge_1.entity;

import br.com.fiap.api.tech_challenge_1.enums.Gender;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private int age;
    private Gender gender;
    private String CPF;
    private String email;
    private String phoneNumber;
    private UUID psychologistId;

    public Patient() {}

    public Patient(UUID id, String name, int age, Gender gender, String CPF, String email, String phoneNumber, UUID psychologistId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.CPF = CPF;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.psychologistId = psychologistId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UUID getPsychologistId() {
        return this.psychologistId;
    }

    public void setPsychologistId(UUID id) {
        this.psychologistId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", CPF='" + CPF + '\'' +
                '}';
    }
}
