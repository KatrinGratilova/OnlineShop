package org.katrin.onlineshop.repository;

import org.katrin.onlineshop.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
}
