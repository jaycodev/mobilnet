package com.sistema.gpon.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class SessionFilter implements Filter {

    private static final List<String> PUBLIC_ALWAYS = List.of(
            "/acceso-denegado", "/css/", "/assets/", "/webjars/"
    );

    private static final List<String> PUBLIC_WHEN_NOT_LOGGED = List.of(
            "/cuenta/login", "/cuenta/iniciarSesion"
    );

    private static final List<String> RUTAS_ADMIN = List.of("/registros", "/clientes", "/planes", "/promociones", "/usuarios");
    private static final List<String> RUTAS_BACKOFFICE = List.of("/registros", "/clientes", "/planes", "/promociones");
    private static final List<String> RUTAS_CONSULTOR = List.of("/registros", "/clientes");
    private static final List<String> RUTAS_SUPERVISOR = List.of("/registros", "/clientes");
    private static final List<String> RUTAS_EDICION_REGISTRO = List.of("/registros/edicion", "/registros/guardar");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("idUsuario") != null);

        boolean isPublicAlways = PUBLIC_ALWAYS.stream().anyMatch(uri::startsWith);
        boolean isPublicWhenNotLogged = PUBLIC_WHEN_NOT_LOGGED.stream().anyMatch(uri::equals);

        if (isPublicAlways) {
            chain.doFilter(request, response);
            return;
        }

        if (!isLoggedIn) {
            if (!isPublicWhenNotLogged) {
                res.sendRedirect(req.getContextPath() + "/cuenta/login");
                return;
            }
        }

        if (isLoggedIn && isPublicWhenNotLogged) {
            res.sendRedirect(req.getContextPath() + "/");
            return;
        }

        if (isLoggedIn) {
            Integer idRol = (Integer) session.getAttribute("idRol");

            if (idRol != null && !tieneAccesoPorRol(idRol, uri)) {
                res.sendRedirect(req.getContextPath() + "/acceso-denegado");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean tieneAccesoPorRol(int idRol, String uri) {
        if (
            uri.equals("/") ||
            uri.startsWith("/cuenta/perfil") ||
            uri.startsWith("/cuenta/actualizar-perfil") ||
            uri.startsWith("/cuenta/actualizar-contrasena") ||
            uri.equals("/cuenta/cerrarSesion") ||
            uri.startsWith("/fragments/") ||
            uri.equals("/acceso-denegado") ||
            uri.equals("")
        ) {
            return true;
        }

        if (RUTAS_EDICION_REGISTRO.stream().anyMatch(uri::startsWith)) {
            return idRol == 1 || idRol == 4;
        }

        List<String> rutasPermitidas = switch (idRol) {
            case 1 -> RUTAS_BACKOFFICE;
            case 2 -> RUTAS_CONSULTOR;
            case 3 -> RUTAS_SUPERVISOR;
            case 4 -> RUTAS_ADMIN;
            default -> List.of();
        };

        return rutasPermitidas.stream().anyMatch(uri::startsWith);
    }
}
