/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.DTO;

/**
 *
 * @author marco-romero
 */
public class UsuarioInfoDTO {

    private Integer id;
    private String nombre;
    private String apPaterno;

    public UsuarioInfoDTO(Integer id, String nombre, String apPaterno) {
        this.id = id;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
    }

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

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

}
