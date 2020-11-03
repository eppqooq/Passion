package Passion.Spring.repository;

import Passion.Spring.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaReviewRepository extends JpaRepository<Review, Long>, ReviewRepository{
    @Override
    Optional<Review> findByNo(Long no);
    @Override
    void deleteByNo(Long no);

}
