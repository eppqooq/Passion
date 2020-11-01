package Passion.Spring.service;

import Passion.Spring.Form.LoginForm;
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
            return "로그인실패1";
        }
        else if(member.get().getPassword() == loginForm.getPassword()) // id로 찾은 DB 값의 password가 폼의 password와 같을 경우
            {
                //세션 처리
                return "로그인성공";
            }
            else // id로 찾은 DB값의 password가 폼의 password와 다를 경우
            {
                return "로그인실패2";
            }

    }

}
