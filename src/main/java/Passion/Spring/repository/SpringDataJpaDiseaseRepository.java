package Passion.Spring.repository;

import Passion.Spring.domain.Board;
import Passion.Spring.domain.Disease;
import Passion.Spring.domain.Hospital;
import Passion.Spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaDiseaseRepository extends JpaRepository<Disease, Long>, DiseaseRepository{
    @Override
    Optional<Disease> findByNo(Long no);
}
