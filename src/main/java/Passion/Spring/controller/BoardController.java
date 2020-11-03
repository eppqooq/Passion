package Passion.Spring.controller;

import Passion.Spring.Form.BoardForm;
import Passion.Spring.domain.Board;
import Passion.Spring.domain.Hospital;
import Passion.Spring.domain.Member;
import Passion.Spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("board")
public class BoardController extends AdminBoardController {

    BoardService boardService;
    AdminMemberService adminMemberService;
    AdminHospitalService adminHospitalService;
    @Autowired
    public BoardController(BoardService boardService, AdminMemberService adminMemberService,
                           AdminHospitalService adminHospitalService)
    {
        super(boardService);
        this.boardService=boardService;
        this.adminMemberService=adminMemberService;
        this.adminHospitalService=adminHospitalService;
    }
    @GetMapping("list") // main에서 게시판 검색 또는 게시판 메뉴를 클릭해서 들어올 때
    public String boardList(HttpSession session, Model model)
    {
        String searchText = (String) session.getAttribute("searchText");
        session.removeAttribute("searchText"); //세션부터 삭제하시고
        if (searchText!=null) // searchText의 값이 있다면
        {

            List<A> fucking = new ArrayList<>();
            List<Board> boards = boardService.findBoards();
            List<Board> contentContainBoards = new ArrayList<>();
            List<Board> titleContainBoards = new ArrayList<>();
            List<String> members = new ArrayList<>();
            Optional<Member> helper = null;

            for (Board board : boards) // 전체 게시판들 중에
            {
                if(board.getContent().contains(searchText) == true) // 컨텐츠에 searchText를 포함한 애가 있나요?
                    contentContainBoards.add(board);                       // 있으면 containBoards에 넣으세요.
            }
            
            for (Board board : contentContainBoards) { //모든 게시판 자료 중에

                helper = adminMemberService.findByNo(board.getMember_no()); // 해당 게시판의 작성자를 받는 optional helper.
                if (helper.isPresent()) {
                    members.add(helper.get().getId()); // 빈 배열 members에 해당 게시판의 작성자를 추가한다.
                    A fuck = new A(board, helper.get()); // 해당 게시판 객체와 작성자 객체를 갖고 생성되는 fuck 객체.
                    fucking.add(fuck); // fuck들을 가지고 있는 fucking 객체의 배열에 요소 하나 추가.
                }
            } // -> fucking이 모든 보드와 보드에 해당하는 작성자를 가지고 있게끔 함

            model.addAttribute("fucking", fucking);
        }
        else  // searchText의 값이 없다면
        {
            List<A> fucking = new ArrayList<>();
            List<Board> boards = boardService.findBoards();
            List<String> members = new ArrayList<>();
            Optional<Member> helper = null;

            for (Board board : boards) {
                helper = adminMemberService.findByNo(board.getMember_no());
                if (helper.isPresent()) {
                    members.add(helper.get().getId());
                    A fuck = new A(board, helper.get());
                    fucking.add(fuck);
                }
            }
            model.addAttribute("fucking", fucking);
        }
        return "main/board_list";
    }
    @PostMapping("list")
    public String searchedBoard(@RequestParam("searchText") String searchText,
                                @RequestParam("searchKindOf") String searchKindOf,
                                        Model model)
    {
        List<A> fucking = new ArrayList<>();
        List<Board> boards = boardService.findBoards();
        List<String> members = new ArrayList<>();
        Optional<Member> helper = null;
        System.out.println("searchText = " + searchText);
        System.out.println("searchKindOf = " + searchKindOf);
        if (searchKindOf.equals("내용"))  // 내용 검색일 경우
        {
            List<Board> contentContainBoards = new ArrayList<>();
            System.out.println("내용으로 옴");
            for (Board board : boards) // 전체 게시판들 중에
            {
                if (board.getContent() != null)
                if (board.getContent().contains(searchText) == true) // 내용에 searchText를 포함한 애가 있나요?
                    contentContainBoards.add(board);                       // 있으면 containBoards에 넣으세요.
            }

            for (Board board : contentContainBoards) { //모든 게시판 자료 중에

                helper = adminMemberService.findByNo(board.getMember_no()); // 해당 게시판의 작성자를 받는 optional helper.
                if (helper.isPresent()) {
                    members.add(helper.get().getId()); // 빈 배열 members에 해당 게시판의 작성자를 추가한다.
                    A fuck = new A(board, helper.get()); // 해당 게시판 객체와 작성자 객체를 갖고 생성되는 fuck 객체.
                    fucking.add(fuck); // fuck들을 가지고 있는 fucking 객체의 배열에 요소 하나 추가.
                }
            } // -> fucking이 모든 보드와 보드에 해당하는 작성자를 가지고 있게끔 함
            model.addAttribute("fucking",fucking);
        }
        else if (searchKindOf.equals("제목"))// searchKindOf == "제목"
        {
            System.out.println("제목으로 옴");
            List<Board> titleContainBoards = new ArrayList<>();
            for (Board board : boards) // 전체 게시판들 중에
            {
                if (board.getTitle()!=null)
                    if (board.getTitle().contains(searchText) == true) // 제목에 searchText를 포함한 애가 있나요?
                    {
                        System.out.println("board.getTitle() = " + board.getTitle());
                        titleContainBoards.add(board);                       // 있으면 titleBoards에 넣으세요.
                    }
            }

            for (Board board : titleContainBoards) { //걸러진 게시판 자료 중에

                helper = adminMemberService.findByNo(board.getMember_no()); // 해당 게시판의 작성자를 받는 optional helper.
                if (helper.isPresent()) {
                    members.add(helper.get().getId()); // 빈 배열 members에 해당 게시판의 작성자를 추가한다.
                    A fuck = new A(board, helper.get()); // 해당 게시판 객체와 작성자 객체를 갖고 생성되는 fuck 객체.
                    fucking.add(fuck); // fuck들을 가지고 있는 fucking 객체의 배열에 요소 하나 추가.
                }
            } // -> fucking이 모든 보드와 보드에 해당하는 작성자를 가지고 있게끔 함
            model.addAttribute("fucking",fucking);

        }
        model.addAttribute("searchText",searchText);
        model.addAttribute("searchKindOf",searchKindOf);

        return "main/board_list";
    }

    @GetMapping("view")
    public String boardView(@RequestParam("no") Long no, Model model)
    {
        Optional <Board> board = boardService.findByNo(no);
        Optional <Member> member = adminMemberService.findByNo(board.get().getMember_no());
        //Optional <Hospital> hospital = adminHospitalService.findByNo(board.get().get());
        if (board.get().getViews()!=null)
                board.get().setViews(board.get().getViews()+1);
        boardService.updateBoard(board.get());
        model.addAttribute("board",board);
        model.addAttribute("member",member);
        return "main/board_view";
    }

    @GetMapping("edit")
    public String boardEdit(@RequestParam("no") Long no, Model model)
    {
        Optional <Board> board = boardService.findByNo(no);
        model.addAttribute("board",board);
        return "main/board_edit";
    }
    @GetMapping ("create")
    public String boardCreate(HttpSession session)
    {
        Long loginedMemberNo = (Long) session.getAttribute("loginedMemberNo");
        System.out.println("loginedMemberNo = " + loginedMemberNo);
        if(loginedMemberNo == null) // 로그인이 되어있지 않으면
        {
            return "redirect:/login"; // 로그인창으로 슝~
        }
        else // 로그인이 되어있으면
        {
            return "main/board_create_form";// create Form으로 가버렷
        }


    }
    @PostMapping ("create")
    public String boardSave(HttpSession session, BoardForm boardForm,Model model)
    {
        Long loginedMemberNo = (Long) session.getAttribute("loginedMemberNo");
        if (loginedMemberNo==null)
        {
            model.addAttribute("message","로그인이 필요한 서비스입니다.");
            return "redirect:/login";
        }
        else
        {
            Board board=new Board();
            boardService.createFormBoardObject(board, boardForm);
            board.setMember_no(loginedMemberNo);
            boardService.updateBoard(board);
            return "redirect:list";
        }
    }

    @GetMapping("delete")
    @Transactional
    public String boardDelete(@RequestParam("no") Long no, Model model)
    {
        boardService.deleteByNo(no);
        return "redirect:/list";
    }
    public class A
    {
        private Board board;
        private Member member;
        public A(Board board, Member member)
        {
            this.board=board;
            this.member=member;
        }
        public Member getMember() {
            return member;
        }

        public void setMember(Member member) {
            this.member = member;
        }

        public Board getBoard() {
            return board;
        }

        public void setBoard(Board board) {
            this.board = board;
        }
    }

}
