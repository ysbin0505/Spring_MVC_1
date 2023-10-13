/**
 * ControllerV2의 반환 타입이 MyView 이므로 프론트 컨트롤러는 컨트롤러의 호출 결과로 MyView 를 반환
 * 받는다. 그리고 view.render() 를 호출하면 forward 로직을 수행해서 JSP가 실행된다
 */
package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
  private Map<String, ControllerV2> controllerMap = new HashMap<>();
  public FrontControllerServletV2() {
    controllerMap.put("/front-controller/v2/members/new-form", new
        MemberFormControllerV2());
    controllerMap.put("/front-controller/v2/members/save", new
        MemberSaveControllerV2());
    controllerMap.put("/front-controller/v2/members", new
        MemberListControllerV2());
  }
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse
      response)
      throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    ControllerV2 controller = controllerMap.get(requestURI);
    if (controller == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }
    MyView view = controller.process(request, response);
    view.render(request, response);
  }
}
