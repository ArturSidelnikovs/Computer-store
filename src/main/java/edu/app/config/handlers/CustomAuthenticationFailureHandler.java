package edu.app.config.handlers;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        UrlPathHelper helper = new UrlPathHelper();
        String contextPath = helper.getContextPath(request);
        if (exception instanceof DisabledException) {
            response.sendRedirect(contextPath + "/login?disabled=true");
        } else {
            response.sendRedirect(contextPath + "/login?error=true");

        }
    }
}
