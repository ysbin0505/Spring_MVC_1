/**
 * 이제 각 컨트롤러는 복잡한 dispatcher.forward() 를 직접 생성해서 호출하지 않아도 된다. 단순히
 * MyView 객체를 생성하고 거기에 뷰 이름만 넣고 반환하면 된다.
 * ControllerV1 을 구현한 클래스와 ControllerV2 를 구현한 클래스를 비교해보면, 이 부분의 중복이
 * 확실하게 제거된 것을 확인할 수 있다.
 */

package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
  @Override
  public MyView process(HttpServletRequest request, HttpServletResponse
      response) throws ServletException, IOException {
    return new MyView("/WEB-INF/views/new-form.jsp");
  }
}
