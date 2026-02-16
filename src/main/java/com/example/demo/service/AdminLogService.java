/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.DTO.AdminLogDTO;
import com.example.demo.entity.AdminLog;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marco-romero
 */
public interface AdminLogService {

    /*LISTAR Movimientos del Usuario*/
    public List<AdminLog> findAllB(Date desde, Date hasta);

    public List<AdminLogDTO> searchOperationByDate(String operacion, Date fechaInicio, Date fechaFin);
}
