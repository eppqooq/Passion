package Passion.Spring.repository;

import Passion.Spring.domain.Disease;


import java.util.List;
import java.util.Optional;

public interface DiseaseRepository {
    Disease save(Disease disease);
    Optional<Disease> findByNo(Long no);
    List<Disease> findAll();
    void deleteByNo(Long no);
}
