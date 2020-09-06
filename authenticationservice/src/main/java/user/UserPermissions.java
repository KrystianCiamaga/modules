package user;

public enum UserPermissions {
    READ("read"),
    WRITE_READ("write:read"),
    ;

    private String permission;

    UserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
