package Application.Entity.Roles;

import Application.Entity.AbstractEntity;
import Application.Entity.Roles.Enum.RoleNameEnum;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class RoleEntity extends AbstractEntity {

        @NotNull
        private RoleNameEnum name;

        public RoleEntity() {}

        public RoleEntity(RoleNameEnum name) {
            this.name = name;
        }

        public RoleNameEnum getName() {
            return name;
        }

        public void setName(RoleNameEnum name) {
            this.name = name;
        }
    }


