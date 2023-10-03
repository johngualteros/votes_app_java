package com.project.app_java.themes.models;

import com.project.app_java.shared.utils.Generators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "theme")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    @Column(nullable = false)
    private String uuid = Generators.generateUUID();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Override
    public String toString() {
        return this.name;
    }

}
