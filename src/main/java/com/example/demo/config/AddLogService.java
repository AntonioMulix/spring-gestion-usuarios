/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.config;

import com.example.demo.entity.AdminLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marco-romero
 */
@Service
public class AddLogService implements LogService {

    @Autowired
    private AddLogRepository AddLogRepository;

    @Override
    public AdminLog save(AdminLog adminlog) {
        return AddLogRepository.save(adminlog);
    }
}
