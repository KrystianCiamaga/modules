package kc.microservice;

public enum Role {

    DEVELOPER("ROLE_DEVELOPER"),
    CLIENT("ROLE_CLIENT"),
    SYSTEM("ROLE_SYSTEM");

    private String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
