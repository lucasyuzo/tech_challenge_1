package br.com.fiap.api.tech_challenge_1.controller.expection;

public class SchedulingNotValidException extends RuntimeException {

    public SchedulingNotValidException(String message) {
        super(message);
    }
}
