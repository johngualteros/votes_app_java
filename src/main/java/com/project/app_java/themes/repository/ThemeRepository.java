package com.project.app_java.themes.repository;

import com.project.app_java.themes.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Query(value = "SELECT * FROM theme WHERE name = ?1 LIMIT 1", nativeQuery = true)
    Theme findByName(String name);
    @Query(value = "SELECT * FROM theme WHERE uuid = ?1 LIMIT 1", nativeQuery = true)
    Optional<Theme> findByUUID(String uuid);
}
