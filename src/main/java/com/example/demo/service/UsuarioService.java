package com.example.demo.service;

import com.example.demo.DTO.UsuarioGuardarDTO;
import com.example.demo.entity.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author marco-romero
 */
public interface UsuarioService {
    // ****************** CRUD de Usuarios ***********************

    //Listar todos los usuarios
    public List<Usuario> findAll();

    //Guardar nuevo usuario
    public Usuario save(UsuarioGuardarDTO usuario);

    //Encontrar un usuario por id
    public Usuario findOne(Integer id);

    //Ver si el usuario esta activo por busqueda de id
    public Boolean activo(Integer id);

    //Eliminar usuario
    public void delete(Integer id);

    //Buscar usuario por username
    public Optional<Usuario> getByUsername(String username);

    //Verificar sie xiste el usuario por id
    public boolean existById(int id);

      //Verificar si existe usuario por username
//    public Usuario existsByUsername(String username);

    //Actualizar password de usuario
    public Usuario updatePassword(Integer id, String password);

}
