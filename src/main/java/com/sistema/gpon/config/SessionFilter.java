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

    private static final List<String> EXCLUDED_URLS = List.of(
            "/cuenta/login",
            "/cuenta/iniciarSesion",
            "/cuenta/registro",
            "/css/", "/js/", "/images/", "/webjars/"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("idUsuario") != null);
        boolean isPublic = EXCLUDED_URLS.stream().anyMatch(uri::startsWith);

        if (isLoggedIn && (uri.equals("/cuenta/login") || uri.equals("/cuenta/iniciarSesion"))) {
            res.sendRedirect(req.getContextPath() + "/");
            return;
        }

        if (!isLoggedIn && !isPublic) {
            res.sendRedirect(req.getContextPath() + "/cuenta/login");
            return;
        }

        chain.doFilter(request, response);
    }
}
