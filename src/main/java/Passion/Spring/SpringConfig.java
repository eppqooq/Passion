package Passion.Spring;

import Passion.Spring.controller.*;
import Passion.Spring.domain.Board;
import Passion.Spring.repository.*;
import Passion.Spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final KindRepository kindRepository;
    private final HospitalRepository hospitalRepository;
    private final BoardRepository boardRepository;
    private final ReviewRepository reviewRepository;
    private final ReplyRepository replyRepository;

    /*
    //1. Repository 선언
    private final Repository Repository;
    2. SpringConfig 생성자 인자값 추가
    Repository Repository;
    3. SpringConfig 생성자의 내용 추가
    this.Repository = Repository;
    4. Controller 등록
    @Bean
    public Admin_Controller admin_Controller()
    {
        return new Admin_Controller(admin_Service());
    }
    5. Service 등록
    @Bean
    public Admin_Service admin_Service()
    {
        return new Admin_Service(boardRepository);
    }
     */


    @Autowired
    public SpringConfig(MemberRepository memberRepository, KindRepository kindRepository,
                        HospitalRepository hospitalRepository, BoardRepository boardRepository,
                        ReviewRepository reviewRepository, ReplyRepository replyRepository)
    {
        this.memberRepository = memberRepository;
        this.kindRepository = kindRepository;
        this.hospitalRepository = hospitalRepository;
        this.boardRepository = boardRepository;
        this.reviewRepository = reviewRepository;
        this.replyRepository = replyRepository;
    }
    @Bean
    public AdminReplyController adminReplyController()
    {
        return new AdminReplyController(adminReplyService());
    }
    @Bean
    public AdminReplyService adminReplyService()
    {
        return new AdminReplyService(replyRepository);
    }



    @Bean
    public AdminReviewController adminReviewController()
    {
        return new AdminReviewController(adminReviewService());
    }
    @Bean
    public AdminReviewService adminReviewService()
    {
        return new AdminReviewService(reviewRepository);
    }



    @Bean
    public AdminBoardController adminBoardController()
    {
        return new AdminBoardController(adminBoardService());
    }

    @Bean
    public AdminBoardService adminBoardService()
    {
        return new AdminBoardService(boardRepository);
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
