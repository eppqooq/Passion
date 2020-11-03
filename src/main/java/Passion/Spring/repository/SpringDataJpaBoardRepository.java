package Passion.Spring.repository;

import Passion.Spring.domain.Board;
import Passion.Spring.domain.Hospital;
import Passion.Spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaBoardRepository extends JpaRepository<Board, Long>, BoardRepository{
    @Override
    Optional<Board> findByNo(Long no);
    @Query ("SELECT m From Member m")
    List <Member> members();
    @Override
    List<Board> findByContentContainingIgnoreCaseOrTitleContainingIgnoreCase(String title, String content);
    @Override
    List<Board> findByTitleContaining(String title);
    @Override
    Optional <List<Board>> findByContentContaining(String content);

}
