package Passion.Spring;

import Passion.Spring.controller.AdminHospitalController;
import Passion.Spring.controller.AdminKindController;
import Passion.Spring.controller.AdminMemberController;
import Passion.Spring.repository.HospitalRepository;
import Passion.Spring.repository.KindRepository;
import Passion.Spring.repository.MemberRepository;
import Passion.Spring.service.AdminHospitalService;
import Passion.Spring.service.AdminKindService;
import Passion.Spring.service.AdminMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final KindRepository kindRepository;
    private final HospitalRepository hospitalRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository, KindRepository kindRepository, HospitalRepository hospitalRepository) {
        this.memberRepository = memberRepository;
        this.kindRepository = kindRepository;
        this.hospitalRepository = hospitalRepository;
    }
    @Bean
    public AdminHospitalController adminHospitalController()
    {
        return new AdminHospitalController(adminHospitalService());
    }
    @Bean
    public AdminHospitalService adminHospitalService()
    {
        return new AdminHospitalService(hospitalRepository);
    }


    @Bean //@Service 등록
    public AdminKindController adminKindController()
    {
        return new AdminKindController(adminKindService());
    }
    @Bean
    public AdminKindService adminKindService()
    {
        return new AdminKindService(kindRepository);
    }


    @Bean //@Controller 등록
    public AdminMemberController adminMemberController()
    {
        return new AdminMemberController(adminMemberService());
    }
    @Bean
    public AdminMemberService adminMemberService()
    {
        return new AdminMemberService(memberRepository);
    }


}
