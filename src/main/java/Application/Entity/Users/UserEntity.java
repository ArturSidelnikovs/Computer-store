package Application.Entity.Users;
import Application.Entity.AbstractEntity;
import Application.Entity.Roles.RoleEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class UserEntity extends AbstractEntity {

    @Size (min = 6, max = 36)
    private String login;

    @Size (min = 6, max = 36)
    private String password;

    @Column(name = "created")
    @NotNull
    private LocalDate created;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id_fk"), inverseJoinColumns = @JoinColumn(name = "role_id_fk"))
    private Set<RoleEntity> roles = new HashSet<>();

    @Override
    public void init() {
        super.init();
        this.created = LocalDate.now();
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedOn() {
        return created;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.created = created;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

}
