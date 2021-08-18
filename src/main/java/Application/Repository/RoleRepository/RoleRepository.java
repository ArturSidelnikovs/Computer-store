package Application.Repository.RoleRepository;


import Application.Entity.Roles.RoleEntity;
import Application.Entity.Roles.Enum.RoleNameEnum;
import Application.Repository.BaseRepository;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<RoleEntity, Long> {

        Optional<RoleEntity> findByName(RoleNameEnum name);
        Optional<RoleEntity> findById(Long id);


}


