package edu.app.model.role;

public enum AccessForRoles {

    READ("read:only"),
    WRITE("write:only");

    private final String  access;

    AccessForRoles(String access) {
        this.access = access;
    }

    public String getAccess() {
        return access;
    }
}
