package Passion.Spring.service;

import Passion.Spring.controller.ReplyForm;
import Passion.Spring.domain.Reply;
import Passion.Spring.repository.ReplyRepository;

import javax.transaction.Transactional;
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
}
