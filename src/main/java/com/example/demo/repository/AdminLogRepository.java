/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.DTO.AdminLogDTO;
import com.example.demo.entity.AdminLog;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author marco-romero
 */
public interface AdminLogRepository extends JpaRepository<AdminLog, Object> {

    @Query(value = "SELECT * FROM admin_log al WHERE date(al.createdAt) "
            + "BETWEEN :desde AND :hasta", nativeQuery = true)
    List<AdminLog> findAllB(@Param("desde") Date desde, @Param("hasta") Date hasta);

    //Selecciona una operación en especifico realizada por un usuario en cierto periodo de fechas
    @Query(value = "SELECT new com.example.demo.DTO.AdminLogDTO("
            + "LogAdministrador.resultParams AS idIncidenciaGenerada, "
            + "RolesDescripcion.name AS creatorsRole, "
            + "LogAdministrador.userName) "
            + "FROM AdminLog AS LogAdministrador "
            + "JOIN UsuarioRol AS RelacionUsuarioRoles ON LogAdministrador.userId = RelacionUsuarioRoles.usuarioId "
            + "JOIN Rol AS RolesDescripcion ON RelacionUsuarioRoles.rolId = RolesDescripcion.id "
            + "WHERE LogAdministrador.operation = :operacion "
            + "AND  createdAt >= :fechaInicio "
            + "AND createdAt <= :fechaFin")
    List<AdminLogDTO> searchOperationByDate(@Param("operacion") String operacion,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin);
}
