/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.serviceImpl;

import cn.elegent.security.token.core.PasswordEncoder;
import com.example.demo.DTO.UsuarioGuardarDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
        user.setApPaterno(usuarioDTO.getApMaterno());
        user.setApMaterno(usuarioDTO.getApMaterno());
        user.setUsername(usuarioDTO.getUsername());
        user.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        user.setActivo(Boolean.TRUE);
        user.setRoles(usuarioDTO.getRoles());
        return usuarioRepository.save(user);
    }

    //Encontrar un registro
    @Transactional(readOnly = true)
    @Override
    public Usuario findOne(Integer id) {
        return usuarioRepository.findById(id).get();
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
        Usuario findUsuario = usuarioRepository.findById(id).get();
        usuarioRepository.delete(findUsuario);
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

}
