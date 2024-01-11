package br.com.fiap.api.tech_challenge_1.controller.expection;

public class ControllerNotFoundException extends RuntimeException {

    public ControllerNotFoundException(String message) {
        super(message);
    }
}
