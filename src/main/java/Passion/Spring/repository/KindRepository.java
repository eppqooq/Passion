package Passion.Spring.repository;

import Passion.Spring.domain.Kind;

import java.util.List;
import java.util.Optional;

public interface KindRepository {
    Kind save(Kind kind);
    Optional<Kind> findByName(String name);
    Optional<Kind> findByNo(Long no);
    void deleteByNo(Long no);
    List<Kind> findAll();
}
