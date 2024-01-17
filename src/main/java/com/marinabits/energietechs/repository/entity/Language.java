package com.marinabits.energietechs.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "LANGUAGE")
@RequiredArgsConstructor
public class Language {

    private String id;
    private String name;

    public Language(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name", length = 200, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
