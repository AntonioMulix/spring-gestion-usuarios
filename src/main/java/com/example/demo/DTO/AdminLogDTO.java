/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.DTO;

import com.example.demo.util.RolNombre;

/**
 *
 * @author marco-romero
 */
public class AdminLogDTO {

    private String idIncidenciaGenerada;
    private RolNombre creatorsRole;
    private String userName;

    public AdminLogDTO() {
    }

    public AdminLogDTO(String idIncidenciaGenerada, RolNombre creatorsRole, String userName) {
        this.idIncidenciaGenerada = idIncidenciaGenerada;
        this.creatorsRole = creatorsRole;
        this.userName = userName;
    }

    public String getIdIncidenciaGenerada() {
        return idIncidenciaGenerada;
    }

    public void setIdIncidenciaGenerada(String idIncidenciaGenerada) {
        this.idIncidenciaGenerada = idIncidenciaGenerada;
    }

    public RolNombre getCreatorsRole() {
        return creatorsRole;
    }

    public void setCreatorsRole(RolNombre creatorsRole) {
        this.creatorsRole = creatorsRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
