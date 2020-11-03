package Passion.Spring.repository;

import Passion.Spring.domain.Hospital;
import Passion.Spring.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaHospitalRepository extends JpaRepository<Hospital, Long>, HospitalRepository{
    @Override
    Optional<Hospital> findByName(String name);
    @Override
    long count();
    @Override
    Optional<Hospital> findByNo(Long no);
    @Override
    void deleteByNo(Long no);
}
