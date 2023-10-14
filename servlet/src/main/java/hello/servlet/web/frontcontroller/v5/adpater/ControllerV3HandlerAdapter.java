package hello.servlet.web.frontcontroller.v5.adpater;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
  @Override
  public boolean supports(Object handler) {     // ControllerV3 을 처리할 수 있는 어댑터를 뜻한다.
    return (handler instanceof ControllerV3);
  }
  @Override
  public ModelView handle(HttpServletRequest request, HttpServletResponse
      response, Object handler) {
    ControllerV3 controller = (ControllerV3) handler;         //handler를 컨트롤러 V3로 변환한 다음에 V3 형식에 맞도록 호출한다.
    Map<String, String> paramMap = createParamMap(request);   //supports() 를 통해 ControllerV3 만 지원하기 때문에 타입 변환은 걱정없이 실행해도 된다.
    ModelView mv = controller.process(paramMap);              //ControllerV3는 ModelView를 반환하므로 그대로 ModelView를 반환하면 된다
    return mv;
  }
  private Map<String, String> createParamMap(HttpServletRequest request) {
    Map<String, String> paramMap = new HashMap<>();
    request.getParameterNames().asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName,
            request.getParameter(paramName)));
    return paramMap;
  }
}
