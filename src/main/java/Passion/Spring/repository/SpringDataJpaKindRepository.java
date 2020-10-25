package Passion.Spring.repository;

import Passion.Spring.domain.Kind;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaKindRepository extends JpaRepository<Kind, Long>, KindRepository{
    @Override
    Optional<Kind> findByName(String name);
    @Override
    Optional<Kind> findByNo(Long no);
    @Override
    void deleteByNo(Long no);
}
