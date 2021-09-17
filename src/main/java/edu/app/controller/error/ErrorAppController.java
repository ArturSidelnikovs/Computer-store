package edu.app.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorAppController implements ErrorController {

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        return "errors/access-denied";
    }

    @RequestMapping("/error")
    @ExceptionHandler(Throwable.class)
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errors/error_404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {

                return "errors/error_505";
            }

        }
        return "/errors/error";
    }
}