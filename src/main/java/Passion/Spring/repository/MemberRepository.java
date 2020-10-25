package Passion.Spring.repository;

import Passion.Spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByName(String name);
    Optional<Member> findByRank(Integer rank);
    Optional<Member> findByNo(Long no);
    void deleteByNo(Long no);
    List<Member> findAll();
}
