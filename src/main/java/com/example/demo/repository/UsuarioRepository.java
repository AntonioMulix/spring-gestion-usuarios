/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marco-romero
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //Listar todos los usuarios
    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    public List<Usuario> listAllUsuarios();

    //Tipo optional esta consulta es para verificar que el usuario exista y este activo
    @Query(value = "SELECT * FROM usuarios u WHERE u.username =:username"
            + " AND u.activo = 1", nativeQuery = true)
    public Optional<Usuario> findByUsername(@Param("username") String username);

    //Tipo boolean, esta consulra es para verificar que el usuario exista y este activo
    @Query(value = "SELECT * FROM usuarios u WHERE u.username = :username "
            + " AND u.activo = 1", nativeQuery = true)
    Usuario existByUsername(@Param("username") String username);

    //Tipo Usuario, esta consulta es para verficar la session activa
    @Query(value = "SELECT * FROM usuarios u WHERE u.username = :username"
            + " AND u.activo = 1", nativeQuery = true)
    Usuario findByUsuarioSession(@Param("username") String username);
}
