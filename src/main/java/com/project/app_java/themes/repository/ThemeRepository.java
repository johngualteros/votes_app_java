package com.project.app_java.themes.repository;

import com.project.app_java.themes.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Query("SELECT t FROM Theme t WHERE t.name = ?1")
    Theme findByName(String name);
    @Query("SELECT t FROM Theme t WHERE t.uuid = ?1")
    Theme findByUUID(String uuid);
}
