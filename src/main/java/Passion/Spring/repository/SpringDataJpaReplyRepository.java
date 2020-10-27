package Passion.Spring.repository;

import Passion.Spring.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaReplyRepository extends JpaRepository<Reply, Long>, ReplyRepository{
    @Override
    void deleteByNo(Long no);
    @Override
    Reply save(Reply reply);
    @Override
    Optional<Reply> findByNo(Long no);
}
