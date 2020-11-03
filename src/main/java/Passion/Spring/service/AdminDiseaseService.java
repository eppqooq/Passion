package Passion.Spring.service;

import Passion.Spring.Form.DiseaseForm;
import Passion.Spring.domain.Disease;
import Passion.Spring.repository.DiseaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminDiseaseService
{
    private final DiseaseRepository diseaseRepository;

    public AdminDiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public List<Disease> findDiseases()
    {
        return diseaseRepository.findAll();
    }


    public Optional<Disease> findByNo(Long no)
    {
        return diseaseRepository.findByNo(no);
    }

    public void updateDisease(Disease disease)
    {
        diseaseRepository.save(disease);
    }

    public void deleteByNo(Long no) {  diseaseRepository.deleteByNo(no); }
    public Disease editFormDiseaseObject(Disease disease, DiseaseForm diseaseForm)
    {
//        String createDay = diseaseForm.getCreateDay().toString().substring(0,5)
//                +diseaseForm.getCreateDay().toString().substring(6,8)
//                +diseaseForm.getCreateDay().toString().substring(9,10);
//        String updateDay = diseaseForm.getUpdateDay().toString().substring(0,5)
//                +diseaseForm.getUpdateDay().toString().substring(6,8)
//                +diseaseForm.getUpdateDay().toString().substring(9,10);


        disease.setContent(diseaseForm.getContent());
        disease.setSmall(diseaseForm.getSmall());
        disease.setPicture(diseaseForm.getPicture());
        disease.setTitle(diseaseForm.getTitle());
        disease.setNo(diseaseForm.getNo());
        return disease;
    }
//    public Member editFormMemberObject(Member member, MemberForm memberForm)
//        {
//        String tel = memberForm.getTel1() + memberForm.getTel2() + memberForm.getTel3();
//        String birthday = memberForm.getBirth_year() + memberForm.getBirth_month() + memberForm.getBirth_day();
//
//        member.setNo(memberForm.getNo());
//        member.setId(memberForm.getId());
//        member.setPassword(memberForm.getPassword());
//        member.setName(memberForm.getName());
//        member.setAddress(memberForm.getAddress());
//        member.setRank(memberForm.getRank());
//        member.setKinds(memberForm.getKinds());
//        member.setBirthday(birthday);
//        member.setTel(tel);
//        return member;
//    }
//    public void updateMember(Member member)
//    {
//        memberRepository.save(member);
//    }
//
//    public void deleteByNo(Long no)
//    {
//        memberRepository.deleteByNo(no);
//    }
//
//    public Long join(Member member)
//    {
//        validateDuplicateMember(member); // 중복 회원 검증
//        memberRepository.save(member);
//        return member.getNo();
//    }
//    private void validateDuplicateMember(Member member) {
//        memberRepository.findByName(member.getName())
//                .ifPresent(m-> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
//    }
}
