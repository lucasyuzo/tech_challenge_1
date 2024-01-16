package br.com.fiap.api.tech_challenge_1.ddd.application.controller.expection;

public class ValidationMessage {

    private String field;
    private String message;

    public ValidationMessage() {}

    public ValidationMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
