package Passion.Spring.repository;

import Passion.Spring.domain.Board;
import Passion.Spring.domain.Member;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board save(Board board);
    Optional<Board> findByNo(Long no);
    List<Board> findAll();
    void deleteByNo(Long no);
    List <Member> members();
    List <Board> findByContentContainingIgnoreCaseOrTitleContainingIgnoreCase(String content, String Title);
    List<Board> findByTitleContaining(String title);
    Optional<List<Board>> findByContentContaining(String content);
    // Containing : 포함하고 있는지
    // IgnoreCase : 대소문자 구별 X
    // Or : 또는..?

}
