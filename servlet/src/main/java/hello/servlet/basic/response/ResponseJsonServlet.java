/**
 * API JSON 뿌리기 -> 나중엔 엄청 간편해짐
 */

package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");  //Json형식으로 볼 떄
    resp.setCharacterEncoding("utf-8");

    HelloData helloData = new HelloData();
    helloData.setUsername("Yu");
    helloData.setAge(23);

    String result = objectMapper.writeValueAsString(helloData);
    resp.getWriter().write(result);

  }
}
