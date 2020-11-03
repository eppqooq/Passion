package Passion.Spring.service;

import Passion.Spring.Form.BoardForm;
import Passion.Spring.domain.Board;
import Passion.Spring.domain.Member;
import Passion.Spring.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BoardService extends AdminBoardService {

    BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository) {
        super(boardRepository);
    }

    public Optional<List<Board>> findByContent(String content)
    {
        return boardRepository.findByContentContaining(content);
    }

    public List<Board> findByContentOrTitle(String content)
    {
        return boardRepository.findByContentContainingIgnoreCaseOrTitleContainingIgnoreCase(content,content);
    }

    public Board createFormBoardObject(Board board, BoardForm boardForm)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateStr = dateFormat.format(date);

        board.setAvailable(boardForm.getAvailable());
        board.setContent(boardForm.getContent());

        if (boardForm.getCreate_day()==null) board.setCreate_day(dateStr);
        else board.setCreate_day(boardForm.getCreate_day());

        if (boardForm.getUpdate_day()==null) board.setUpdate_day(dateStr);
        else board.setUpdate_day(boardForm.getUpdate_day());
        board.setKind_no(boardForm.getKind_no());
        board.setMember_no(boardForm.getMember_no());

        board.setPicture(boardForm.getPicture());

        board.setTitle(boardForm.getTitle());

        if (boardForm.getViews() == null) board.setViews(0);
        else board.setViews(boardForm.getViews());
        board.setNo(boardForm.getNo());
        board.setFileName(boardForm.getFileName());
        return board;
    }

    public Board updateFormBoardObject(Board board, BoardForm boardForm)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateStr = dateFormat.format(date);

        Optional <Board> helper = findByNo(boardForm.getNo());
        board = helper.get();
        board.setFileName(boardForm.getFileName());
        board.setNo(boardForm.getNo());
        board.setContent(boardForm.getContent());
        board.setUpdate_day(dateStr);
        return board;
    }




}
