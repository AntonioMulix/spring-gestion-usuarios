/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.serviceImpl;

import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.DTO.UsuarioGuardarDTO;
import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.RolRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import com.example.demo.util.RolNombre;
import java.time.LocalDate;
import java.util.HashSet;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author marco-romero
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Listar todos los usuarios
    @Transactional(readOnly = true)
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.listAllUsuarios();
    }

    //Guardar nuevo usuario
    @Transactional
    @Override
    public Usuario save(UsuarioGuardarDTO usuarioDTO) {
        Usuario user = new Usuario();
        user.setNombre(usuarioDTO.getNombre());
        user.setApPaterno(usuarioDTO.getApPaterno());
        user.setApMaterno(usuarioDTO.getApMaterno());
        user.setUsername(usuarioDTO.getUsername());
        user.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        user.setActivo(Boolean.TRUE);
        Rol rolUser = rolRepository.findByName(RolNombre.ROLE_USER).get();
        Set<Rol> roles = new HashSet<>();
        roles.add(rolUser);
        user.setRoles(roles);
        return usuarioRepository.save(user);
    }

    //Encontrar un registro
    @Transactional(readOnly = true)
    @Override
    public Usuario findOne(Integer id) {
        return usuarioRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Usuario updateUsuario(Integer id, UsuarioDTO usuarioDTO) {
        Usuario user = usuarioRepository.findById(id).get();
        user.setNombre(usuarioDTO.getNombre());
        user.setApPaterno(usuarioDTO.getApPaterno());
        user.setApMaterno(usuarioDTO.getApMaterno());
        user.setUsername(usuarioDTO.getUsername());
        return usuarioRepository.save(user);
    }

    //Cambiar estatus de usuario
    @Transactional
    @Override
    public Integer estatusUsuario(Integer id) {

        Usuario getUsuario = usuarioRepository.findById(id).get();
        getUsuario.setActivo(Boolean.FALSE);
        //Retornar un 1 en caso de exito
        return 1;
    }

    //Ver si es activo o no
    @Transactional(readOnly = true)
    @Override
    public Boolean activo(Integer id) {
        Usuario findUser = usuarioRepository.findById(id).get();
        Boolean resultado = findUser.getActivo();
        return resultado;
    }

    //Eliminar usuario
    @Transactional
    @Override
    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }

    //Buscar usuario por username
    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> getByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    //Verificar si existe el usuario por id
    @Transactional(readOnly = true)
    @Override
    public boolean existById(int id) {
        Usuario findUsuario = usuarioRepository.findById(id).get();
        return findUsuario != null;
    }

    //Actualizar Password
    @Transactional
    @Override
    public Usuario updatePassword(Integer id, String password) {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setPassword(passwordEncoder.encode(password));
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario findByUsuarioSession(String username) {
        return usuarioRepository.findByUsuarioSession(username);
    }

}
