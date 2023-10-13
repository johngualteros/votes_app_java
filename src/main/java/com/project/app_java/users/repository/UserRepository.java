package com.project.app_java.users.repository;

import com.project.app_java.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM user WHERE email = ?1 LIMIT 1", nativeQuery = true)
    User findByEmail(String email);
    @Query(value = "SELECT * FROM user WHERE name = ?1 LIMIT 1", nativeQuery = true)
    User findByName(String name);
    @Query(value = "SELECT * FROM user WHERE uuid = ?1 LIMIT 1", nativeQuery = true)
    User findByUUID(String uuid);
}
