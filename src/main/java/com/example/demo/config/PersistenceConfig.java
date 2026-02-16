package com.example.demo.config;

import com.example.demo.entity.AdminLog;
import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class PersistenceConfig {

    private static final Logger logger = LoggerFactory.getLogger(PersistenceConfig.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AddLogService addLogService;

    @Pointcut("@annotation(com.example.demo.config.SystemControllerLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        AdminLog adminLog = new AdminLog();
        Object proceed = null;

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemControllerLog systemControllerLog = method.getAnnotation(SystemControllerLog.class);

        // ==============================
        // 1️⃣ Información de anotación
        // ==============================
        if (systemControllerLog != null) {
            adminLog.setType(systemControllerLog.type());
            adminLog.setOperation(systemControllerLog.operation());
        }

        // ==============================
        // 2️⃣ Información HTTP (si existe)
        // ==============================
        ServletRequestAttributes attributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            String requestUri = request.getRequestURI();
            String requestMethod = request.getMethod();
            String remoteAddr = getIpAddress(request);

            adminLog.setRemoteAddr(remoteAddr);
            adminLog.setRequestUri(requestUri);
            adminLog.setMethod(requestMethod);

            logger.info("IP Cliente: {}", remoteAddr);
            logger.info("URI: {}", requestUri);
            logger.info("HTTP Method: {}", requestMethod);
        }

        // ==============================
        // 3️⃣ Parámetros del método
        // ==============================
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            StringBuilder paramsBuilder = new StringBuilder();
            for (Object obj : args) {
                if (obj != null) {
                    paramsBuilder.append(obj.toString()).append(" | ");
                }
            }
            adminLog.setParams(paramsBuilder.toString());
            logger.info("Parámetros: {}", paramsBuilder);
        }

        // ==============================
        // 4️⃣ Fecha de creación
        // ==============================
        adminLog.setCreatedAt(LocalDateTime.now());

        // ==============================
        // 5️⃣ Usuario autenticado
        // ==============================
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Usuario usuario = usuarioService.findByUsuarioSession(username);

            if (usuario != null) {
                adminLog.setUserId(usuario.getId());
                adminLog.setUserName(username);
            }
        }

        // ==============================
        // 6️⃣ Ejecución del método interceptado
        // ==============================
        try {
            proceed = joinPoint.proceed();

            adminLog.setResultParams(
                    proceed != null ? proceed.toString() : "null"
            );
            adminLog.setExceptionLog("Sin anormalidad");

        } catch (Throwable throwable) {

            logger.error("Error capturado en AOP: ", throwable);

            adminLog.setType("Err");
            adminLog.setExceptionLog(throwable.getMessage());
            adminLog.setResultParams("Error");

            throw throwable; // 🔥 IMPORTANTE: re-lanzar excepción
        } finally {
            try {
                addLogService.save(adminLog);
            } catch (Exception e) {
                logger.error("Error guardando log: ", e);
            }
        }

        logger.info("Retorno del método: {}", proceed);

        return proceed;
    }

    public String getIpAddress(HttpServletRequest request) {

        String ipAddress = request.getHeader("x-forwarded-for");

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();

            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                try {
                    InetAddress inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    logger.error("No se pudo obtener IP local", e);
                }
            }
        }

        // Si hay múltiples IPs (proxy), tomar la primera
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }

        return ipAddress;
    }
}
