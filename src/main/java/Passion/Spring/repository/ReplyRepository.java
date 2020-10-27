package Passion.Spring.repository;

import Passion.Spring.domain.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    void deleteByNo(Long no);
    Reply save(Reply reply);
    List<Reply> findAll();
    Optional<Reply> findByNo(Long no);
}
