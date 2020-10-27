package Passion.Spring.controller;

import Passion.Spring.domain.Board;
import Passion.Spring.domain.Member;
import Passion.Spring.domain.Review;
import Passion.Spring.service.AdminReviewService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RequestMapping("admin/review")
public class AdminReviewController {
    AdminReviewService adminReviewService;
    public AdminReviewController(AdminReviewService adminReviewService)
    {
        this.adminReviewService = adminReviewService;
    }

    @GetMapping("list")
    public String reviewList(Model model)
    {
        List<Review> reviews = adminReviewService.findReviews();
        model.addAttribute("reviews",reviews);
        return "admin/admin_review_list";
    }

    @GetMapping("view")
    public String reviewView(@RequestParam Long no, Model model)
    {
        Optional<Review> review = adminReviewService.findByNo(no);
        model.addAttribute("review",review);
        return "admin/admin_review_view";
    }
    @GetMapping("edit")
    public String reviewEdit(@RequestParam Long no, Model model)
    {
        Optional<Review> review = adminReviewService.findByNo(no);
        model.addAttribute("review",review);
        return "admin/admin_review_edit";
    }
    @PostMapping("update")
    public String reviewUpdate(@RequestParam Long no, ReviewForm reviewForm)
    {
        Review review = new Review();
        review = adminReviewService.editFormReviewObject(review,reviewForm);
        adminReviewService.updateReview(review);
        return "redirect:list";
    }
    @PostMapping("delete")
    @Transactional
    public String reviewDelete(@RequestParam Long no)
    {
        adminReviewService.deleteByNo(no);
        return "redirect:list";
    }
}
