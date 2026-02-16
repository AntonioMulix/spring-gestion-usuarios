/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author marco-romero
 */
@Entity
@Table(name = "admin_log")
public class AdminLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;
    @Column(name = "type")
    private String type;
    @Column(name = "operation")
    private String operation;
    @Column(name = "remote_addr")
    private String remoteAddr;
    @Column(name = "request_uri")
    private String requestUri;
    @Column(name = "method")
    private String method;
    @Column(name = "params")
    private String params;
    @Column(name = "operate_date")
    private LocalDateTime createdAt;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "result_params")
    private String resultParams;
    @Column(name = "exception_log")
    private String exceptionLog;

    public AdminLog() {
    }

    public AdminLog(Integer logId, String type, String operation, String remoteAddr, String requestUri, String method, String params, LocalDateTime createdAt, Integer userId, String userName, String resultParams, String exceptionLog) {
        this.logId = logId;
        this.type = type;
        this.operation = operation;
        this.remoteAddr = remoteAddr;
        this.requestUri = requestUri;
        this.method = method;
        this.params = params;
        this.createdAt = createdAt;
        this.userId = userId;
        this.userName = userName;
        this.resultParams = resultParams;
        this.exceptionLog = exceptionLog;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResultParams() {
        return resultParams;
    }

    public void setResultParams(String resultParams) {
        this.resultParams = resultParams;
    }

    public String getExceptionLog() {
        return exceptionLog;
    }

    public void setExceptionLog(String exceptionLog) {
        this.exceptionLog = exceptionLog;
    }

}
