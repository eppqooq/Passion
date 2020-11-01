package Passion.Spring.service;


import Passion.Spring.Form.ReviewForm;
import Passion.Spring.domain.Review;
import Passion.Spring.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

public class AdminReviewService {
    private final ReviewRepository reviewRepository;
    public AdminReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    public List<Review> findReviews()
    {
        return reviewRepository.findAll();
    }
    public Optional<Review> findByNo(Long no)
    {
        return reviewRepository.findByNo(no);
    }

    public Review updateReview(Review review)
    {
        return reviewRepository.save(review);
    }
    public void deleteByNo(Long no)
    {
        reviewRepository.deleteByNo(no);
    }



    public Review editFormReviewObject(Review review, ReviewForm reviewForm)
    {
        review.setAvailable(reviewForm.getAvailable());
        review.setContent(reviewForm.getContent());
        review.setCreate_day(reviewForm.getCreate_day());
        review.setHospital_no(reviewForm.getHospital_no());
        review.setMember_no(reviewForm.getMember_no());
        review.setNo(reviewForm.getNo());
        review.setScore(reviewForm.getScore());
        review.setUpdate_day(reviewForm.getUpdate_day());
        return review;
    }
//    public List<Kind> findKinds()
//    {
//        return kindRepository.findAll();
//    }
//    public Optional<Kind> findByNo(Long no)
//    {
//        return kindRepository.findByNo(no);
//    }
//    public Kind editFormKindObject(Kind kind, KindForm kindForm)
//    {
//        kind.setNo(kindForm.getNo());
//        kind.setName(kindForm.getName());
//        return kind;
//    }
//    public void updateKind(Kind kind)
//    {
//        kindRepository.save(kind);
//    }
//
//    public void deleteByNo(Long no)
//    {
//        kindRepository.deleteByNo(no);
//    }
}
