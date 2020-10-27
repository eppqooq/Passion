package Passion.Spring.repository;

import Passion.Spring.domain.Board;
import Passion.Spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board save(Board board);
    Optional<Board> findByNo(Long no);
    List<Board> findAll();
    void deleteByNo(Long no);
}
