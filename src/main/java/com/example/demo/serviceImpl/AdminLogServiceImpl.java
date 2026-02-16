/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.serviceImpl;

import com.example.demo.DTO.AdminLogDTO;
import com.example.demo.entity.AdminLog;
import com.example.demo.repository.AdminLogRepository;
import com.example.demo.service.AdminLogService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marco-romero
 */
@Service
public class AdminLogServiceImpl implements AdminLogService {

    @Autowired
    AdminLogRepository adminLogRepository;

    @Transactional(readOnly = true)
    @Override
    public List<AdminLog> findAllB(Date desde, Date hasta) {
        return adminLogRepository.findAllB(desde, hasta);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AdminLogDTO> searchOperationByDate(String operacion, Date fechaInicio, Date fechaFin) {
        return adminLogRepository.searchOperationByDate(operacion, fechaInicio, fechaFin);
    }

}
