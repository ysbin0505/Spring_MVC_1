/**
 * 컨트롤러(Controller) 핸들러(Handler)
 * 이전에는 컨트롤러를 직접 매핑해서 사용했다. 그런데 이제는 어댑터를 사용하기 때문에, 컨트롤러 뿐만
 * 아니라 어댑터가 지원하기만 하면, 어떤 것이라도 URL에 매핑해서 사용할 수 있다. 그래서 이름을
 * 컨트롤러에서 더 넒은 범위의 핸들러로 변경했다.
 */

package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adpater.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adpater.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
  private final Map<String, Object> handlerMappingMap = new HashMap<>();
  private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
  public FrontControllerServletV5() { //생성자는 핸들러 매핑과 어댑터를 초기화(등록)한다.
    initHandlerMappingMap();
    initHandlerAdapters();
  }
  private void initHandlerMappingMap() {
    handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new
        MemberFormControllerV3());
    handlerMappingMap.put("/front-controller/v5/v3/members/save", new
        MemberSaveControllerV3());
    handlerMappingMap.put("/front-controller/v5/v3/members", new
        MemberListControllerV3());

    //V4 추가
    handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new
        MemberFormControllerV4());
    handlerMappingMap.put("/front-controller/v5/v4/members/save", new
        MemberSaveControllerV4());
    handlerMappingMap.put("/front-controller/v5/v4/members", new
        MemberListControllerV4());
  }

  private void initHandlerAdapters() {
    handlerAdapters.add(new ControllerV3HandlerAdapter());
    handlerAdapters.add(new ControllerV4HandlerAdapter()); //V4 추가
  }
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse
      response)
      throws ServletException, IOException {
    Object handler = getHandler(request);
    if (handler == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }
    MyHandlerAdapter adapter = getHandlerAdapter(handler);
    ModelView mv = adapter.handle(request, response, handler);
    MyView view = viewResolver(mv.getViewName());
    view.render(mv.getModel(), request, response);
  }
  private Object getHandler(HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    return handlerMappingMap.get(requestURI);
  }
  private MyHandlerAdapter getHandlerAdapter(Object handler) {
    for (MyHandlerAdapter adapter : handlerAdapters) {
      if (adapter.supports(handler)) {
        return adapter;
      }
    }
    throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
  }
  private MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }
}