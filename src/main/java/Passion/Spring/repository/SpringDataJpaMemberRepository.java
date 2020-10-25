package Passion.Spring.repository;

import Passion.Spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    @Override
    Optional<Member> findByName(String name);
    @Override
    Optional<Member> findByRank(Integer rank);
    @Override
    Optional<Member> findByNo(Long no);
    @Override
    void deleteByNo(Long no);
}
