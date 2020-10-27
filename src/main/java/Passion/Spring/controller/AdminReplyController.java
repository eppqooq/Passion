package Passion.Spring.controller;

import Passion.Spring.domain.Reply;
import Passion.Spring.domain.Review;
import Passion.Spring.service.AdminReplyService;
import Passion.Spring.service.AdminReviewService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RequestMapping("admin/reply")
public class AdminReplyController {
    AdminReplyService adminReplyService;
    public AdminReplyController(AdminReplyService adminReplyService)
    {
        this.adminReplyService = adminReplyService;
    }

    @GetMapping("list")
    public String replyList(Model model)
    {
        List<Reply> replys = adminReplyService.findReplys();
        model.addAttribute("replys",replys);
        return "admin/admin_reply_list";
    }

    @GetMapping("view")
    public String replyView(@RequestParam Long no, Model model)
    {
        Optional<Reply> reply = adminReplyService.findByNo(no);
        model.addAttribute("reply",reply);
        return "admin/admin_reply_view";
    }
    @GetMapping("edit")
    public String replyEdit(@RequestParam Long no, Model model)
    {
        Optional<Reply> reply = adminReplyService.findByNo(no);
        model.addAttribute("reply",reply);
        return "admin/admin_reply_edit";
    }
    @PostMapping("update")
    public String replyUpdate(@RequestParam Long no, ReplyForm replyForm)
    {
        Reply reply = new Reply();
        reply = adminReplyService.editFormReplyObject(reply,replyForm);
        adminReplyService.updateReply(reply);
        return "redirect:list";
    }
    @PostMapping("delete")
    @Transactional
    public String replyDelete(@RequestParam Long no)
    {
        adminReplyService.deleteByNo(no);
        return "redirect:list";
    }
}
