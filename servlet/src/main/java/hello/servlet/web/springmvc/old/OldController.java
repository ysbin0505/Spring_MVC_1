package hello.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Component("/springmvc/old-controller") //spring bean의 이름(OldController)을 url형태로 바꾸는것
public class OldController implements Controller {

  @Override
  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    System.out.println("OldController.handleRequest");
    return new ModelAndView("new-form");  //뷰 리졸버 (OldController - View 조회할 수 있도록 변경)
  }                                                 //return new ModelAndView("/WEB-INF/views/new-form.jsp"); 느낌임
}
