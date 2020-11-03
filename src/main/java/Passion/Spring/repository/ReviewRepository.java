package Passion.Spring.repository;

import Passion.Spring.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository  {
    Review save(Review review);
    List<Review> findAll();
    Optional<Review> findByNo(Long no);
    void deleteByNo(Long no);


}
