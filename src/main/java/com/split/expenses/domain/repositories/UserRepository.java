package com.split.expenses.domain.repositories;

import com.split.expenses.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
