package com.example.demo.service;

import com.example.demo.entity.Rol;
import com.example.demo.util.RolNombre;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author marco-romero
 */
public interface RolService {

    //Guardar nuevo rol
    public void save(Rol rol);

    //Obtener Rol por nombre
    public Optional<Rol> getRolByNombre(RolNombre name);

    //Verificar si existe el rol
    public boolean existsByRolNombre(RolNombre name);

    //Listar los roles
    public List<Rol> findAll();
}
