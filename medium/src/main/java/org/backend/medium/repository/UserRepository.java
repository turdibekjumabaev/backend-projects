package org.backend.medium.repository;

import org.backend.medium.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findFirstById(Integer id);
    Optional<User> findFirstByUsername(String username);

}