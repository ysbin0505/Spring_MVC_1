package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //[status-line]
    response.setStatus(HttpServletResponse.SC_OK);
    //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

    //[response-headers]
    response.setHeader("Content-Type", "text/plain;charset=utf-8");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("my-header", "hello");

    PrintWriter writer = response.getWriter();
    writer.println("안녕하세여");
  }

  private void content(HttpServletResponse response){
    response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
  }

  private void cookie(HttpServletResponse response){
    Cookie cookie = new Cookie("myCookie", "good");
    cookie.setMaxAge(600);
    response.addCookie(cookie);
  }

  private void redirect(HttpServletResponse response) throws IOException{
    response.sendRedirect("/basic/hello-form.html");
  }
}
