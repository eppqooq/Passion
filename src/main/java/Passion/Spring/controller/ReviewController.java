package Passion.Spring.controller;

import Passion.Spring.Form.ReplyForm;
import Passion.Spring.Form.ReviewForm;
import Passion.Spring.domain.Member;
import Passion.Spring.domain.Review;
import Passion.Spring.service.AdminMemberService;
import Passion.Spring.service.AdminReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("review")
public class ReviewController extends AdminReviewController {
    AdminMemberService adminMemberService;
    public ReviewController(AdminReviewService adminReviewService,
                            AdminMemberService adminMemberService) {
        super(adminReviewService);
        this.adminMemberService=adminMemberService;
    }
    @PostMapping("register")
    public String reviewRegister(HttpSession session, @RequestParam("no") Long no, ReviewForm reviewForm, Model model)
    {
        Review review = new Review();
        Long loginedMemberNo = (Long) session.getAttribute("loginedMemberNo");
        if (loginedMemberNo == null || loginedMemberNo == 0) // 세션이 없으면
        {
            model.addAttribute("message", "로그인이 필요한 서비스입니다.");// message -> 로그인이 필요한 서비스입니다.
            return "redirect:/login";
        }
        else // 세션이 있으면
        {

            adminReviewService.registReviewForm(review, reviewForm);
            review.setMember_no((loginedMemberNo));
            review.setHospital_no(no);
//            Long loginedMemberNo = (Long) session.getAttribute("loginedMemberNo");
//            Optional <Member> member = adminMemberService.findByNo(loginedMemberNo);
//            review.setMember_no(member.get().getNo());
            model.addAttribute("message", "리뷰가 성공적으로 등록되었습니다.");
        }
        System.out.println("review = " + review);
        System.out.println("review.getContent() = " + review.getContent());
        System.out.println("loginedMemberNo = " + loginedMemberNo);
        adminReviewService.updateReview(review); // update는 no 값이 없으면 save한다.
        return "redirect:/hospital/view?no="+no;
    }

}
