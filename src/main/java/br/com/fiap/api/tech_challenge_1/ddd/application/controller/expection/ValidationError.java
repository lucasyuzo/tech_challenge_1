package br.com.fiap.api.tech_challenge_1.ddd.application.controller.expection;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private final List<ValidationMessage> messages = new ArrayList<ValidationMessage>();

    public List<ValidationMessage> getMessages() {
        return messages;
    }

    public void addMessage(String field, String message) {
        messages.add(new ValidationMessage(field, message));
    }
}
