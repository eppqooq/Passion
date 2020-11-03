package Passion.Spring.service;

import Passion.Spring.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends AdminMemberService{
    public MemberService(MemberRepository memberRepository) {
        super(memberRepository);
    }
}
