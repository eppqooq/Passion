package Passion.Spring.service;


import Passion.Spring.Form.ReviewForm;
import Passion.Spring.domain.Review;
import Passion.Spring.repository.ReviewRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Review saveReview(Review review)
    {
        return reviewRepository.save(review);
    }
    public void deleteByNo(Long no)
    {
        reviewRepository.deleteByNo(no);
    }

    public Review registReviewForm(Review review, ReviewForm reviewForm)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        review.setAvailable(reviewForm.getAvailable());
        review.setContent(reviewForm.getContent());
        review.setCreate_day(dateStr);
        review.setHospital_no(reviewForm.getHospital_no());
        review.setMember_no(reviewForm.getMember_no());
        review.setScore(reviewForm.getScore());
        review.setUpdate_day(dateStr);

        return review;
    }
    
    
    
    public Review editFormReviewObject(Review review, ReviewForm reviewForm)
    {
        review.setNo(reviewForm.getNo());
        review.setAvailable(reviewForm.getAvailable());
        review.setContent(reviewForm.getContent());
        review.setCreate_day(reviewForm.getCreate_day());
        review.setHospital_no(reviewForm.getHospital_no());
        review.setMember_no(reviewForm.getMember_no());
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
