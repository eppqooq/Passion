package Passion.Spring.controller;

import Passion.Spring.Form.ReplyForm;
import Passion.Spring.domain.Reply;
import Passion.Spring.service.AdminReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("reply")
public class ReplyController extends AdminReplyController{

    public ReplyController(AdminReplyService adminReplyService) {
        super(adminReplyService);
    }

    @PostMapping("register")
    public String replyRegister(@RequestParam("boardNo") int boardNo,HttpSession session, ReplyForm replyForm)
    {
        Long no = (Long)session.getAttribute("loginedMemberNo");
        if(no == null) return "redirect:/login";

        else
        {
            Reply reply = new Reply();
            reply = adminReplyService.updateFormReplyObject(reply, replyForm);
            reply.setBoard_no(boardNo);
            adminReplyService.updateReply(reply);
            return "redirect:/board/view?no=" + boardNo; // no는 edit번호.
        }
    }
}
