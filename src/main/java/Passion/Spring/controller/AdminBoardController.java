package Passion.Spring.controller;

import Passion.Spring.Form.BoardForm;
import Passion.Spring.domain.Board;
import Passion.Spring.service.AdminBoardService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RequestMapping("admin/board")
public class AdminBoardController {
    AdminBoardService adminBoardService;
    public AdminBoardController(AdminBoardService adminBoardService) {
        this.adminBoardService = adminBoardService;
    }
    @GetMapping("list1")
    public String boardList(Model model)
    {
        List<Board> boards = adminBoardService.findBoards();

        model.addAttribute("boards",boards);
        return "admin/admin_board_list";
    }


    @GetMapping("view1")
    public String boardView(@RequestParam Long no, Model model)
    {
        Optional<Board> board = adminBoardService.findByNo(no);
        model.addAttribute("board",board);
        return "admin/admin_board_view";
    }
    @GetMapping("edit")
    public String boardEdit(@RequestParam Long no, Model model)
    {
        Optional<Board> board = adminBoardService.findByNo(no);
        model.addAttribute("board",board);
        return "admin/admin_board_edit";
    }

    @PostMapping("update1")
    public String boardUpdate(BoardForm boardForm)
    {
        Board board = new Board();
        board = adminBoardService.editFormBoardObject(board,boardForm);
        adminBoardService.updateBoard(board);
        return "redirect:list";
    }

    @PostMapping("delete")
    @Transactional
    public String memberDelete(@RequestParam("no") Long no)
    {
        adminBoardService.deleteByNo(no);
        return "redirect:list";
    }
}
