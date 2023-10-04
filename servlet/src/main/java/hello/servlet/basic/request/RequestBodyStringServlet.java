package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletInputStream inputStream = request.getInputStream();  //메세지 Body의 내용을 가져옴
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //문자로 바꿀 때 어떤 언어를 쓰는지 알아야함. 요즘은 UTF-8
    System.out.println("messageBody = " + messageBody);
    response.getWriter().write("complete");
  }
}
