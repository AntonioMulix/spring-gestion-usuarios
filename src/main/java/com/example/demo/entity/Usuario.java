package com.example.demo.entity;

import com.example.demo.util.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author marco-romero
 */
@Entity
@Table(name = "usuarios")
public class Usuario extends DateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    private String apPaterno;

    @NotBlank
    @Column(nullable = false)
    private String apMaterno;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private Boolean activo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    // ====================
    // Constructores
    // ====================
    protected Usuario() {
        //requerido por JPA
    }

    //constructor
    private Usuario(
            String nombre,
            String apPaterno,
            String apMaterno,
            String username,
            String password,
            Boolean activo
    ) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.username = username;
        this.password = password;
        this.activo = activo;
    }

    /**
     * Método fábrica para crear instancias de Usuario de forma controlada.
     * Centraliza la construcción del objeto y evita el uso directo del
     *
     * @param nombre
     * @param apPaterno
     * @param apMaterno
     * @param username
     * @param password
     * @param activo
     * @return
     */
    public static Usuario crear(
            String nombre,
            String apPaterno,
            String apMaterno,
            String username,
            String password,
            Boolean activo
    ) {
        return new Usuario(
                nombre,
                apPaterno,
                apMaterno,
                username,
                password,
                activo
        );
    }

    //getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActivo() {
        return activo;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

}
