package kc.domain.enums;

public enum Environment {

    PRODUCTION("production"),
    LOCAL("local");


    String description;

    Environment(String description) {
        this.description = description;
    }


}
