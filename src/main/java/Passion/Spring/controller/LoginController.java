package Passion.Spring.controller;

import Passion.Spring.Form.LoginForm;
import Passion.Spring.Form.RegisterForm;
import Passion.Spring.domain.Member;
import Passion.Spring.service.AdminMemberService;
import Passion.Spring.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;
@Controller
public class LoginController {
    LoginService loginService;
    public LoginController(LoginService loginService)
    {
        this.loginService = loginService;
    }
//    @PostMapping("login/check")
//    public String loginCheck(@RequestParam("id") String id, @RequestParam("password") String password, Model model)
//    {
//        Optional <Member> member = loginService.findById(id);
//        if(!member.isPresent()) // 멤버가 없으면
//        {
//             model.addAttribute("message","입력하신 정보로 로그인 할 수 없습니다.");
//             return "redirect:/main/login/form";
//        }
//        else if (member.get().getPassword() == "password")// 조회된 멤버의 password와 폼의 입력한 password가 같으면
//            {
//                return "redirect:/";// 해당 넘버, 아이디로 세션 처리
//            }
//            else // 조회된 멤버가 있지만, 폼에 입력된 password와 member의 password가 다를 때
//            {
//                return "redirect:main/login_form";
//            }
//    }
        @GetMapping("logout")
        public String Logout(HttpSession httpSession)
        {
            httpSession.removeAttribute("loginedMemberNo");
            httpSession.removeAttribute("isLogined");
            return "redirect:/";
        }
        @PostMapping ("login/check")
        public String loginCheck(HttpSession httpSession, LoginForm loginForm, Model model)
        {
            String checkMessage = loginService.loginCheck(loginForm);

            Integer loginedMemberNo = (Integer) httpSession.getAttribute("loginedMemberNo");

            if (loginedMemberNo == null)  //세션이 없다면
            {
                httpSession.setAttribute("loginedMemberNo", 0);     // 0 : 비회원
            }
            if (checkMessage == "로그인성공")
            {
                Optional<Member> member = loginService.findById(loginForm.getId());
                httpSession.setAttribute("loginedMemberNo", member.get().getNo());
                httpSession.setAttribute("isLogined", true);
                model.addAttribute("member", member);
                return "redirect:/";
            }
            else
            {
                model.addAttribute("message", "입력하신 정보로 로그인 할 수 없습니다.");
                model.addAttribute("id",loginForm.getId());
                System.out.println("loginForm.getId() = " + loginForm.getId());
                return "main/login_form";
            }
        }



        @GetMapping("login")
        public String loginForm()
        {

            return "main/login_form";
        }

        @PostMapping("idCheck")
        public String idCheckInRegister(RegisterForm registerForm, Model model)
        {
            Member member = new Member();
            loginService.findById(registerForm.getId());
            member = loginService.registerFormMemberObject(member,registerForm);
            loginService.findById(member.getId());
            return "";
        }
//        @GetMapping("idCheck") // ID 중복 확인 체크
//        public String loginIdCheck(@RequestParam("id") String id, Model model)
//        {
//            Optional<Member> member = loginService.findById(id);
//            if(id.length()<4 || id.length()>10) // 아이디가 4자가 안되거나 10자를 넘는 경우
//            {
//                model.addAttribute("message","아이디는 최소 4자, 최대 10자입니다.");
//                return "redirect:/login";
//            }
//            else if(member.isPresent())
//            {
//                model.addAttribute("message","사용 중인 아이디입니다.");
//                return "redirect:/login";
//            }
//            else
//            {
//                model.addAttribute("message","사용 가능한 아이디입니다.");
//                return "redirect:/register";
//            }
//        }
        @GetMapping ("register")
        public String Register()
        {
            return "/main/register";
        }
}
