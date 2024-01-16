package br.com.fiap.api.tech_challenge_1.ddd.domain.enums;

public enum Gender {
    MALE(0), FEMALE(1), TRANS_GENDER(2), GENDER_QUEER(3),DECLINE_TO_ANSWER(4);

    private final int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
