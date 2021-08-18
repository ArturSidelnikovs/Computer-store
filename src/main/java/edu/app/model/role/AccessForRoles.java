package edu.app.model.role;

public enum AccessForRoles {

    READ_ONLY("read:only"),
    WRITE_ONLY ("write:only");

    private final String  access;

    AccessForRoles(String access) {
        this.access = access;
    }

    public String getAccess() {
        return access;
    }
}
