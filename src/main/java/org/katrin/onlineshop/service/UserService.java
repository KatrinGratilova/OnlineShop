package org.katrin.onlineshop.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.katrin.onlineshop.model.Role;
import org.katrin.onlineshop.model.UserEntity;
import org.katrin.onlineshop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean create(UserEntity userEntity){
        if(userRepository.findByEmail(userEntity.getEmail()) != null) return false;
        userEntity.setActive(true);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.getRoles().add(Role.ROLE_USER);
        log.info("User created: " + userEntity.getEmail());
        userRepository.save(userEntity);
        return true;
    }
}
