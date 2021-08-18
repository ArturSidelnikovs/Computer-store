package edu.app.repository.role_repository;


import edu.app.entity.roles.RoleNameEnum;
import edu.app.repository.BaseRepository;
import edu.app.entity.roles.RoleEntity;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<RoleEntity, Long> {

        Optional<RoleEntity> findByName(RoleNameEnum name);
        Optional<RoleEntity> findById(Long id);


}


