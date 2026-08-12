package filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final List<String> RUTAS_PUBLICAS = Arrays.asList(
            "/login.jsp",
            "/sesion"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("usuario") != null);

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        String uriSinContexto = requestURI.substring(contextPath.length());

        boolean isPublicRoute = RUTAS_PUBLICAS.contains(uriSinContexto);

        if (isLoggedIn || isPublicRoute) {

            chain.doFilter(request, response);
        } else {

            httpResponse.sendRedirect(contextPath + "/login.jsp");
        }

    }

}
