package Passion.Spring.controller;

import Passion.Spring.Form.BoardForm;
import Passion.Spring.domain.Board;
import Passion.Spring.domain.Member;
import Passion.Spring.domain.Reply;
import Passion.Spring.service.*;
import Passion.Spring.tech.Pagination;
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
    AdminReplyService adminReplyService;
    @Autowired
    public BoardController(BoardService boardService, AdminMemberService adminMemberService,
                           AdminHospitalService adminHospitalService, AdminReplyService adminReplyService)
    {
        super(boardService);
        this.boardService=boardService;
        this.adminMemberService=adminMemberService;
        this.adminHospitalService=adminHospitalService;
        this.adminReplyService=adminReplyService;
    }
    @GetMapping("list") // main에서 게시판 검색 또는 게시판 메뉴를 클릭해서 들어올 때
    public String boardList(HttpSession session, Model model, @RequestParam("no") int no)
    {
        String searchText = (String) session.getAttribute("searchText");
        session.removeAttribute("searchText"); //세션부터 삭제하시고
        if (searchText!=null) // searchText의 값이 있다면
        {
            List<A> helpers1 = new ArrayList<>();
            List<Board> boards = boardService.findBoards();
            List<Board> contentContainBoards = new ArrayList<>();
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
                    A temp = new A(board, helper.get()); // 해당 게시판 객체와 작성자 객체를 갖고 생성되는 fuck 객체.
                    helpers1.add(temp); // temp들을 가지고 있는 helpers1 객체의 배열에 요소 하나 추가.
                }
            } // -> helpers1이 모든 보드와 보드에 해당하는 작성자를 가지고 있게끔 함


            Pagination <A> pagination=new Pagination<A>(helpers1,no,helpers1.size(),7, 5);

            System.out.println("페이지 번호 = " + no);
            List<A> help = pagination.paginationObject();
            model.addAttribute("help", help);
            List<Integer> page= pagination.paginationPage();
            model.addAttribute("page",page);
            model.addAttribute("maxPage",helpers1.size());
            System.out.println("page = " + page);
        }
        else  // searchText의 값이 없다면
        {
            List<A> helpers3 = new ArrayList<>();
            List<Board> boards = boardService.findBoards();
            List<String> members = new ArrayList<>();
            Optional<Member> helper = null;

            for (Board board : boards) {
                helper = adminMemberService.findByNo(board.getMember_no());
                if (helper.isPresent()) {
                    members.add(helper.get().getId());
                    A temp = new A(board, helper.get());
                    helpers3.add(temp);
                }
            }
            Pagination <A> pagination=new Pagination<A>(helpers3,no,helpers3.size(),7, 5);
            List<A> help = pagination.paginationObject();
            model.addAttribute("help", help);
            List<Integer> page= pagination.paginationPage();
            model.addAttribute("page",page);
            model.addAttribute("maxPage",pagination.getMaxPageValue());

        }
        return "main/board_list";
    }
    @PostMapping("list")
    public String searchedBoard(@RequestParam("searchText") String searchText,
                                @RequestParam("searchKindOf") String searchKindOf,
                                        Model model)
    {
        
        List<A> help = new ArrayList<>();

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
                    A temp = new A(board, helper.get()); // 해당 게시판 객체와 작성자 객체를 갖고 생성되는 temp 객체.
                    help.add(temp); // fuck들을 가지고 있는 help 객체의 배열에 요소 하나 추가.
                }
            } // -> fucking이 모든 보드와 보드에 해당하는 작성자를 가지고 있게끔 함
            Pagination<A> pagination = new Pagination<A>(help,1,help.size(),7,5);
                    List<A> paginationHelp=pagination.paginationObject();
                    List<Integer> page = pagination.paginationPage();
            model.addAttribute("page",page);
            model.addAttribute("maxPage",pagination.getMaxPageValue());
            model.addAttribute("help",paginationHelp);
            
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
                    A temp = new A(board, helper.get()); // 해당 게시판 객체와 작성자 객체를 갖고 생성되는 temp 객체.
                    help.add(temp); // temp들을 가지고 있는 help 객체의 배열에 요소 하나 추가.
                }
            } // -> fucking이 모든 보드와 보드에 해당하는 작성자를 가지고 있게끔 함
            Pagination<A> pagination = new Pagination<A>(help,1,help.size(),7,5);
            List<A> paginationHelp=pagination.paginationObject();
            List<Integer> page = pagination.paginationPage();
            model.addAttribute("page",page);
            model.addAttribute("maxPage",pagination.getMaxPageValue());
            model.addAttribute("help",paginationHelp);
        }
        model.addAttribute("searchText",searchText);
        model.addAttribute("searchKindOf",searchKindOf);

        return "main/board_list";
    }

    @GetMapping("view")
    public String boardView(HttpSession session, @RequestParam("no") Long no, Model model)
    {
        Long loginedMemberNo = (Long) session.getAttribute("loginedMemberNo");
        Optional <Board> board = boardService.findByNo(no);
        Optional <Member> member = adminMemberService.findByNo(board.get().getMember_no());

        if(loginedMemberNo != null) // 로그인 한 상태일 경우
        {
            if(member.get().getNo().equals(loginedMemberNo) == true )
                model.addAttribute("isMe",true);
            Optional <Member> sessionMember = adminMemberService.findByNo(loginedMemberNo);
                model.addAttribute("sessionMember",sessionMember.get());

        }
        //Optional <Hospital> hospital = adminHospitalService.findByNo(board.get().get());
        List<Reply> replys = adminReplyService.findReplys(); // 전체 reply 조회
        List<Reply> replysWhereBoardNo = new ArrayList<>();
        for (Reply reply : replys)
        {
            if(reply.getBoard_no().toString()
                    .equals(board.get().getNo().toString()))
                replysWhereBoardNo.add(reply); // 해당 보드의 리뷰들의 집합.
        }

        List<ViewHelper> viewHelpers = new ArrayList<>();


        for(Reply reply : replysWhereBoardNo)
        {
            Optional <Member> memberWhereMemberNoInReply
                    = adminMemberService.findByNo((long)reply.getMember_no());
            ViewHelper helper = new ViewHelper(reply, memberWhereMemberNoInReply.get());
            viewHelpers.add(helper);
        }

        if (board.get().getViews()!=null)
                board.get().setViews(board.get().getViews()+1);
        boardService.updateBoard(board.get());

        model.addAttribute("viewHelpers",viewHelpers);
        model.addAttribute("board",board);
        model.addAttribute("member",member);
        return "main/board_view";
    }
    public class ViewHelper
    {
        private Reply reply;
        private Member member;

        public Member getMember() {
            return member;
        }

        public void setMember(Member member) {
            this.member = member;
        }

        ViewHelper(Reply reply, Member member)
        {
            this.reply = reply;
            this.member = member;
        }

        public Reply getReply() {
            return reply;
        }

        public void setReply(Reply reply) {
            this.reply = reply;
        }
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

    @GetMapping("update")
    public String boardUpdate(@RequestParam ("no") Long no, Model model)
    {
        Optional <Board> board = boardService.findByNo(no);
        model.addAttribute("board",board.get());
        return "main/board_update_form";
    }
    @PostMapping("update")
    public String boardUpdate(BoardForm boardForm)
    {
        Board board = null;
        board = boardService.updateFormBoardObject(board, boardForm);
        boardService.updateBoard(board);
        return "redirect:/board/list?no=1";
    }

    @GetMapping("deleteBoard")
    @Transactional
    public String boardDelete1(@RequestParam ("no") Long no)
    {
        boardService.deleteByNo(no);
        return "redirect:/board/list?no=1";
    }

}
