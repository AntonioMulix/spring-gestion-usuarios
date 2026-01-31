package com.example.demo.entity;

import com.example.demo.util.RolNombre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 *
 * @author marco-romero
 */
@Entity
@Table(name = "roles")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RolNombre name;

    public Rol() {
        // requerido por JPA
    }

    public Rol(Integer id, RolNombre name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolNombre getName() {
        return name;
    }

    public void setName(RolNombre name) {
        this.name = name;
    }

}
