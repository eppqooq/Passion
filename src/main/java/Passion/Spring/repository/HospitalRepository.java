package Passion.Spring.repository;

import Passion.Spring.domain.Hospital;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository {
    Hospital save(Hospital hospital);
    Optional<Hospital> findByName(String name);


    Optional<Hospital> findByNo(Long no);

    void deleteByNo(Long no);
    List<Hospital> findAll();
}
