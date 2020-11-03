package Passion.Spring.service;

import Passion.Spring.Form.MemberForm;
import Passion.Spring.domain.Member;
import Passion.Spring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class AdminMemberService
{
    private final MemberRepository memberRepository;

    public AdminMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Optional<Member> findByNo(Long no)
    {
        return memberRepository.findByNo(no);
    }

    public Optional<Member> findById(String id)
    {
        return memberRepository.findById(id);
    }
    public Member editFormMemberObject(Member member, MemberForm memberForm)
        {
        String tel = memberForm.getTel1() + memberForm.getTel2() + memberForm.getTel3();
        String birthday = memberForm.getBirth_year() + memberForm.getBirth_month() + memberForm.getBirth_day();

        member.setNo(memberForm.getNo());
        member.setId(memberForm.getId());
        member.setPassword(memberForm.getPassword());
        member.setName(memberForm.getName());
        member.setAddress(memberForm.getAddress());
        member.setRank(memberForm.getRank());
        member.setKinds(memberForm.getKinds());
        member.setBirthday(birthday);
        member.setTel(tel);
        return member;
    }
    public void updateMember(Member member)
    {
        memberRepository.save(member);
    }


    public void deleteByNo(Long no)
    {
        memberRepository.deleteByNo(no);
    }

    public Long join(Member member)
    {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getNo();
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
