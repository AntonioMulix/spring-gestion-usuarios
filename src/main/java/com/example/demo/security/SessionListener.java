/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.security;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author marco-romero
 */
@Component
public class SessionListener implements HttpSessionListener {
    //5 Minutos
    //300 mls

    private final int sessionExp_secconds = 600;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        System.out.println("session created");
        System.out.println("session id: " + session.getId());
        event.getSession().setMaxInactiveInterval(sessionExp_secconds);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        System.out.println("session destroyed");
        System.out.println("session id: " + session.getId());
    }
}
