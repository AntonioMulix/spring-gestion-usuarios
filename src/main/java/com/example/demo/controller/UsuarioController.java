/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.DTO.UsuarioGuardarDTO;
import com.example.demo.config.SystemControllerLog;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.OutputEntity;
import com.example.demo.service.UsuarioService;
import com.example.demo.util.Response;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marco-romero
 */
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Busca a todos los usuarios activos y no activos
    @SystemControllerLog(operation = "listarUsuarios", type = "obtuvo lista de todos los  usuarios") //Log de quien ejecuta el metodo
    @GetMapping(value = "/listarUsuarios")
    public ResponseEntity<OutputEntity> listarUsuarios() {
        OutputEntity<List<Usuario>> out = new OutputEntity<>();
        try {
            List<Usuario> result = usuarioService.findAll();
            if (result.isEmpty()) {
                out.succes(Response.NOTFOUND.getCode(), Response.NOTFOUND.getKey(), result);

            } else {
                out.succes(Response.OK.getCode(), Response.OK.getKey(), result);
            }
        } catch (Exception e) {
            out.error();

        }
        return new ResponseEntity<>(out, out.getCode());
    }

    //Guardar usuario
    @SystemControllerLog(operation = "guardarUsuario", type = "Guardo un usuario") //Log de quien ejecuta el metodo
    @PostMapping(value = "/guardarUsuario")
    public ResponseEntity<OutputEntity> guardarUsuario(@RequestBody UsuarioGuardarDTO usuarioGuardarDTO) {
        OutputEntity<Usuario> out = new OutputEntity<>();
        try {
            Usuario result = usuarioService.save(usuarioGuardarDTO);
            out.succes(Response.CREATED.getCode(), Response.CREATED.getKey(), result);
        } catch (Exception e) {
            out.error();
        }
        return new ResponseEntity<>(out, out.getCode());
    }

    //Buscar usuario por id
    @GetMapping(value = "/buscarUsuario/{id}")
    public ResponseEntity<OutputEntity> buscarUsuario(@PathVariable Integer id) {
        OutputEntity<Usuario> out = new OutputEntity<>();
        try {
            Usuario result = usuarioService.findOne(id);
            out.succes(Response.OK.getCode(), Response.OK.getKey(), result);
        } catch (Exception e) {
            out.error();
        }
        return new ResponseEntity<>(out, out.getCode());
    }

    //Actualizar datos de usuario
    @PostMapping(value = "/actualizarUsuario/{id}")
    public ResponseEntity<OutputEntity> actualizarUsuario(@RequestBody UsuarioDTO usuario, @PathVariable Integer id) {
        OutputEntity<Usuario> out = new OutputEntity<>();
        try {
            Usuario result = usuarioService.updateUsuario(id, usuario);
            out.succes(Response.UPDATE.getCode(), Response.UPDATE.getKey(), result);
        } catch (Exception e) {
            out.error();
        }
        return new ResponseEntity<>(out, out.getCode());
    }

    //Cambiar estatus de usuario
    @PostMapping(value = "/cambiarEstatusUsuario/{id}")
    public ResponseEntity<OutputEntity> cambiarEstatusUsuario(@PathVariable Integer id) {
        OutputEntity<Integer> out = new OutputEntity<>();
        try {
            Integer result = usuarioService.estatusUsuario(id);
            out.succes(Response.UPDATE.getCode(), Response.UPDATE.getKey(), result);
        } catch (Exception e) {
            out.error();
        }
        return new ResponseEntity<>(out, out.getCode());
    }

    //Eliminar Usuario
    @SystemControllerLog(operation = "eliminarUsuario", type = "Elimino al usuario")
    @DeleteMapping(value = "/eliminarUsuario/{id}")
    public ResponseEntity<OutputEntity> deleteUsuario(@PathVariable Integer id) {
        OutputEntity out = new OutputEntity();
        try {
            usuarioService.delete(id);
            out.succes(Response.DELETED.getCode(), Response.DELETED.getKey(), null);
        } catch (Exception e) {
            out.error();
        }
        return new ResponseEntity<>(out, out.getCode());
    }
}
