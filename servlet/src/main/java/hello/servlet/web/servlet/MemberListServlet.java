/**
 * MemberListServlet 은 다음 순서로 동작한다.
 * 1. memberRepository.findAll() 을 통해 모든 회원을 조회한다.
 * 2. 회원 목록 HTML을 for 루프를 통해서 회원 수 만큼 동적으로 생성하고 응답한다.
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
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

  protected MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    resp.setContentType("text/html");
    resp.setCharacterEncoding("utf-8");

    List<Member> members = memberRepository.findAll();

    PrintWriter w = resp.getWriter();
    w.write("<html>");
    w.write("<head>");
    w.write(" <meta charset=\"UTF-8\">");
    w.write(" <title>Title</title>");
    w.write("</head>");
    w.write("<body>");
    w.write("<a href=\"/index.html\">메인</a>");
    w.write("<table>");
    w.write(" <thead>");
    w.write(" <th>id</th>");
    w.write(" <th>username</th>");
    w.write(" <th>age</th>");
    w.write(" </thead>");
    w.write(" <tbody>");

      /*
 w.write(" <tr>");
 w.write(" <td>1</td>");
 w.write(" <td>userA</td>");
 w.write(" <td>10</td>");
 w.write(" </tr>");
*/
    for (Member member : members) {
      w.write(" <tr>");
      w.write(" <td>" + member.getId() + "</td>");
      w.write(" <td>" + member.getUsername() + "</td>");
      w.write(" <td>" + member.getAge() + "</td>");
      w.write(" </tr>");
    }
    w.write(" </tbody>");
    w.write("</table>");
    w.write("</body>");
    w.write("</html>");
  }
}
