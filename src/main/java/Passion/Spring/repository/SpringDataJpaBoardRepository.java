package Passion.Spring.repository;

import Passion.Spring.domain.Board;
import Passion.Spring.domain.Hospital;
import Passion.Spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaBoardRepository extends JpaRepository<Board, Long>, BoardRepository{
    @Override
    Optional<Board> findByNo(Long no);
}
