package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *
 * @author marco-romero
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    @NotBlank
    private String nombre;

    @Column(name = "apellido")
    @NotBlank
    private String apellido;

    @Column(name = "email")
    @NotBlank
    private String email;

    /*
        El constructor vacio se mantiene protegido
        y es utilizado exclusivamente por JPA/Hibernate.
     */
    protected Cliente() {
        // Requerido por JPA
    }

    //Constructor para inicializar objeto
    private Cliente(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    /*
    * Método fábrica para crear una instancia de Cliente
    * Se utiliza para encapsular la creación del objeto
    * y vontrolar qué atributos pueden ser inicializados,
    * evitando que se creen instancias en un estado inconsistente.
     */
    public static Cliente crear(String nombre, String apellido, String email) {
        return new Cliente(nombre, apellido, email);
    }

    //getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
