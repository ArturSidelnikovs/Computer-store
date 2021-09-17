package edu.app.model.user;

import java.util.Arrays;
import java.util.List;

public enum Role {
    USER(Arrays.asList(Permission.DEVELOPERS_READ)),
    ADMIN(Arrays.asList(Permission.DEVELOPERS_READ,
            Permission.DEVELOPERS_WRITE));


    private final List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
}