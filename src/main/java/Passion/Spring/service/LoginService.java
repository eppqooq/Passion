package Passion.Spring.service;

import Passion.Spring.Form.LoginForm;
import Passion.Spring.Form.MemberForm;
import Passion.Spring.Form.RegisterForm;
import Passion.Spring.controller.AdminMemberController;
import Passion.Spring.domain.Member;
import Passion.Spring.repository.MemberRepository;

import java.util.Optional;

public class LoginService extends AdminMemberService {
    public LoginService(MemberRepository memberRepository) {
        super(memberRepository);
    }

    public String loginCheck(LoginForm loginForm)
    {

        Optional<Member> member = findById(loginForm.getId());
        if(!member.isPresent()) // id로 찾은 DB값이 없을 경우
        {
            System.out.println("실패1");
            return "로그인실패1";
        }
        else  // id로 찾은 DB 값의 password가 폼의 password와 같을 경우
        {
            if (loginForm.getPassword().equals(member.get().getPassword()) == true)
            {
                System.out.println("member.get().getPassword() = " + member.get().getPassword());
                //세션 처리
                System.out.println("성공");

                return "로그인성공";
            }
            else // id로 찾은 DB값의 password가 폼의 password와 다를 경우
            {
                System.out.println("member.get().getPassword() = " + member.get().getPassword());
                System.out.println("실패2");
                return "로그인실패2";
            }
        }
    }
    public Member registerFormMemberObject(Member member, RegisterForm registerForm)
    {
        member.setId(registerForm.getId());
        member.setPassword(registerForm.getPassword());
        member.setName(registerForm.getName());
        member.setAddress(registerForm.getAddress());
        member.setEmail(registerForm.getEmail());
        member.setBirthday(registerForm.getBirthday());
        return member;
    }

}
