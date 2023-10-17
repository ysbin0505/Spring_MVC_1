/**
 * HTML Form 에서 데이터를 입력하고 전송을 누르면 실제 회원 데이터가 저장되도록 함
 * 전송 방식은 POST HTML Form에서 학습한 내용과 같음
 *  동작순서
 *  1. 파라미터를 조회해서 Member 객체를 만든다.
 *  2. Member 객체를 MemberRepository를 통해서 저장한다.
 *  3. Member 객체를 사용해서 결과 화면용 HTML을 동적으로 만들어서 응답한다.
 */

package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("MemberSaveServlet.service");
    String username = req.getParameter("username");
    int age = Integer.parseInt(req.getParameter("age"));

    Member member = new Member(username,age);
    memberRepository.save(member);

    resp.setContentType("text/html");
    resp.setCharacterEncoding("utf-8");
    PrintWriter w = resp.getWriter();
    w.write("<html>\n" +
        "<head>\n" +
        " <meta charset=\"UTF-8\">\n" +
        "</head>\n" +
        "<body>\n" +
        "성공\n" +
        "<ul>\n" +
        " <li>id="+member.getId()+"</li>\n" +
        " <li>username="+member.getUsername()+"</li>\n" +
        " <li>age="+member.getAge()+"</li>\n" +
        "</ul>\n" +
        "<a href=\"/index.html\">메인</a>\n" +
        "</body>\n" +
        "</html>");
  }
}
