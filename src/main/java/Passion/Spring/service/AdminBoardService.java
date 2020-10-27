package Passion.Spring.service;

import Passion.Spring.controller.BoardForm;
import Passion.Spring.controller.MemberForm;
import Passion.Spring.domain.Board;
import Passion.Spring.domain.Member;
import Passion.Spring.repository.BoardRepository;
import Passion.Spring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class AdminBoardService
{
    private final BoardRepository boardRepository;

    public AdminBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findBoards()
    {
        return boardRepository.findAll();
    }


    public Optional<Board> findByNo(Long no)
    {
        return boardRepository.findByNo(no);
    }

    public void updateBoard(Board board)
    {
        boardRepository.save(board);
    }

    public void deleteByNo(Long no) {  boardRepository.deleteByNo(no); }
    public Board editFormBoardObject(Board board, BoardForm boardForm)
    {
//        String createDay = boardForm.getCreateDay().toString().substring(0,5)
//                +boardForm.getCreateDay().toString().substring(6,8)
//                +boardForm.getCreateDay().toString().substring(9,10);
//        String updateDay = boardForm.getUpdateDay().toString().substring(0,5)
//                +boardForm.getUpdateDay().toString().substring(6,8)
//                +boardForm.getUpdateDay().toString().substring(9,10);

        board.setAvailable(boardForm.getAvailable());
        board.setContent(boardForm.getContent());
        board.setCreate_day(boardForm.getCreate_day());
        board.setUpdate_day(boardForm.getUpdate_day());
        board.setKind_no(boardForm.getKind_no());
        board.setMember_no(boardForm.getMember_no());
        board.setPicture(boardForm.getPicture());
        board.setTitle(boardForm.getTitle());
        board.setViews(boardForm.getViews());
        board.setNo(boardForm.getNo());
        return board;
    }
//    public Member editFormMemberObject(Member member, MemberForm memberForm)
//        {
//        String tel = memberForm.getTel1() + memberForm.getTel2() + memberForm.getTel3();
//        String birthday = memberForm.getBirth_year() + memberForm.getBirth_month() + memberForm.getBirth_day();
//
//        member.setNo(memberForm.getNo());
//        member.setId(memberForm.getId());
//        member.setPassword(memberForm.getPassword());
//        member.setName(memberForm.getName());
//        member.setAddress(memberForm.getAddress());
//        member.setRank(memberForm.getRank());
//        member.setKinds(memberForm.getKinds());
//        member.setBirthday(birthday);
//        member.setTel(tel);
//        return member;
//    }
//    public void updateMember(Member member)
//    {
//        memberRepository.save(member);
//    }
//
//    public void deleteByNo(Long no)
//    {
//        memberRepository.deleteByNo(no);
//    }
//
//    public Long join(Member member)
//    {
//        validateDuplicateMember(member); // 중복 회원 검증
//        memberRepository.save(member);
//        return member.getNo();
//    }
//    private void validateDuplicateMember(Member member) {
//        memberRepository.findByName(member.getName())
//                .ifPresent(m-> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
//    }
}
