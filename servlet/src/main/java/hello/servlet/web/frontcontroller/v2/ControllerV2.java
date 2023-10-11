package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {
  MyView process(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException;
}

/**
 String viewPath = "/WEB-INF/views/new-form.jsp";
 RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
 dispatcher.forward(request, response);
 
 V1에 비해 깔끔해짐
 */