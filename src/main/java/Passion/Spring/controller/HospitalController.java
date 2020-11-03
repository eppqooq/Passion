package Passion.Spring.controller;

import Passion.Spring.domain.Hospital;
import Passion.Spring.domain.Member;
import Passion.Spring.domain.Review;
import Passion.Spring.service.AdminHospitalService;
import Passion.Spring.service.AdminMemberService;
import Passion.Spring.service.AdminReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("hospital")
public class HospitalController extends AdminHospitalController{

    AdminReviewService adminReviewService;
    AdminMemberService adminMemberService;
    public HospitalController(AdminHospitalService adminHospitalService,
                              AdminReviewService adminReviewService,
                              AdminMemberService adminMemberService) {
        super(adminHospitalService);
        this.adminReviewService=adminReviewService;
        this.adminMemberService=adminMemberService;
    }
    public class ListHelper
    {
        private long reviewCount;
        private Hospital hospital;
        public ListHelper(long reviewCount, Hospital hospital)
        {
            this.reviewCount = reviewCount;
            this.hospital = hospital;
        }

        public long getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(long reviewCount) {
            this.reviewCount = reviewCount;
        }

        public Hospital getHospital() {
            return hospital;
        }

        public void setHospital(Hospital hospital) {
            this.hospital = hospital;
        }
    }

    @GetMapping("list")
    public String hospitalList(Model model)
    {
        List <Hospital> hospitals = adminHospitalService.findHospitals();
        List <Review> allReviews = adminReviewService.findReviews(); // 전체 리뷰 중
         // 해당 병원의 리뷰들. (count로 개수 구함)
        List <ListHelper> helpers = new ArrayList<>(); // 뷰에 던질 헬퍼들
        for ( Hospital hospital : hospitals)
        {
            List <Review> reviews = new ArrayList<>();
            for(Review review : allReviews)
            {

                if (hospital.getNo() == review.getHospital_no()) // 병원에 해당하는 리뷰이면,
                {
                    reviews.add(review); // reviews에 집어넣는다.
                }

            }
            long count = reviews.size();//해당 병원의 review가 모두 모이면, 그 개수를 센다.
            ListHelper listHelper = new ListHelper(count, hospital); // 리뷰의 개수와 hospital을 가지는 헬퍼에 넣고
            helpers.add(listHelper); // 뷰에 넘길 리스트에 헬퍼를 추가한다.
        }

        model.addAttribute("helpers",helpers); // 병원 전부를 담는 그릇
        return "main/hospital_list";
    }


    @GetMapping("view")
    public String hospital_View(@RequestParam("no") Long no, Model model)
    {
        Optional <Hospital> hospital = adminHospitalService.findByNo(no);
        List<Review> allReview = adminReviewService.findReviews(); // select * from review
        //List<Review> reviews = new ArrayList<>();                  // where hospital_no = hospital.get().getNo()
        List <Helper> helpers = new ArrayList<>();
        List <Review> reviews = new ArrayList<>();
        for ( Review review : allReview)
        {
            if (hospital.isPresent()) // hospital안에 값이 있다면,
            {
                String hospitalName = hospital.get().getName();

                if ( hospital.get().getNo() == review.getHospital_no()) // hospital 번호로 된 리뷰이면,
                {
                    Optional<Member> member = adminMemberService.findByNo(review.getMember_no());
                    if (member.isPresent()) // 해당 리뷰의 member_no로 조회한 member에 값이 있으면,
                    {
                        String memberId = member.get().getId();
                        Helper helper = new Helper(review, hospitalName, memberId);
                        helpers.add(helper);
                        reviews.add(review);
                    }
                }
            }
        }
        model.addAttribute("reviewCount",reviews.size());
        model.addAttribute("hospital",hospital);
        model.addAttribute("helpers",helpers);
        return "main/hospital_view";
    }


    public class Helper // ViewHelper
    {
        private String hospitalName;
        private Review review;
        private String memberId;

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public Helper (Review review, String hospitalName, String memberId)
        {
            this.review = review;
            this.hospitalName = hospitalName;
            this.memberId = memberId;
        }
        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public Review getReview() {
            return review;
        }

        public void setReview(Review review) {
            this.review = review;
        }


    }


}
