package Passion.Spring.repository;

import Passion.Spring.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaHospitalRepository extends JpaRepository<Hospital, Long>, HospitalRepository{
    @Override
    Optional<Hospital> findByName(String name);

    @Override
    Optional<Hospital> findByNo(Long no);
    @Override
    void deleteByNo(Long no);


}
