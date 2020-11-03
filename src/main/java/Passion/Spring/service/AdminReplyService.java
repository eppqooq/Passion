package Passion.Spring.service;

import Passion.Spring.Form.ReplyForm;
import Passion.Spring.domain.Reply;
import Passion.Spring.repository.ReplyRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AdminReplyService {
    ReplyRepository replyRepository;
    public AdminReplyService(ReplyRepository replyRepository)
    {
        this.replyRepository = replyRepository;
    }
    public List<Reply> findReplys()
    {
        return replyRepository.findAll();
    }
    public Optional<Reply> findByNo(Long no)
    {
        return replyRepository.findByNo(no);
    }

    public Reply updateReply(Reply reply)
    {
        return replyRepository.save(reply);
    }

    public void deleteByNo(Long no)
    {
        replyRepository.deleteByNo(no);
    }

    public Reply editFormReplyObject(Reply reply, ReplyForm replyForm)
    {
        reply.setAvailable(replyForm.getAvailable());
        reply.setBoard_no(replyForm.getBoard_no());
        reply.setContent(replyForm.getContent());
        reply.setMember_no(replyForm.getMember_no());
        reply.setNo(replyForm.getNo());
        reply.setPassword(replyForm.getPassword());
        reply.setReg_date(replyForm.getReg_date());
        reply.setWriter_name(replyForm.getWriter_name());
        return reply;
    }
    public Reply updateFormReplyObject(Reply reply, ReplyForm replyForm)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        reply.setAvailable(replyForm.getAvailable()); // 게시판 댓글인 경우 0, 대댓글인 경우 1
        //reply.setBoard_no(replyForm.getBoard_no());
        reply.setContent(replyForm.getContent());
        reply.setMember_no(replyForm.getMember_no());
        //reply.setNo(replyForm.getNo());
        //reply.setPassword(replyForm.getPassword());
        reply.setReg_date(dateStr);
        //reply.setWriter_name(replyForm.getWriter_name());
        return reply;
    }
}
