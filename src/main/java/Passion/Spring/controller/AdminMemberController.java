package Passion.Spring.controller;

import Passion.Spring.domain.Member;
import Passion.Spring.service.AdminMemberService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



//@Controller
//@RequestMapping("admin/member") // default 경로 설정
@RequestMapping("admin/member")
public class AdminMemberController
{
    AdminMemberService adminMemberService;

    public AdminMemberController(AdminMemberService adminMemberService) {
        this.adminMemberService = adminMemberService;
        System.out.println("AdminMemberService.getClass() = " + adminMemberService.getClass());
    }

    @GetMapping("list")
    public String memberList(Model model)
    {
        List<Member> members = adminMemberService.findMembers();
        model.addAttribute("members",members);
        return "admin/admin_member_list";
    }

    @GetMapping("edit")
    public String memberEdit(@RequestParam("no") Long no, Model model)
    {
        Optional<Member> member = adminMemberService.findByNo(no);
        model.addAttribute("member",member);
        return "admin/admin_member_edit";
    }

    @PostMapping("update")
    public String memberUpdate(MemberForm memberForm)
    {
        Member member = new Member();
        member=adminMemberService.editFormMemberObject(member,memberForm);
        adminMemberService.updateMember(member);
        return "redirect:list";
    }
    @GetMapping("view")
    public String memberView(@RequestParam("no") Long no, Model model)
    {
        Optional<Member> member = adminMemberService.findByNo(no);
        model.addAttribute("member",member);

        return "admin/admin_member_view";
    }

    @PostMapping("delete")
    @Transactional
    public String memberDelete(@RequestParam("no") Long no)
    {
        adminMemberService.deleteByNo(no);
        return "redirect:list";
    }

//    @PatchMapping("update")
//    public String memberPatch (MemberForm form)
//    {
//
//    }




//    @GetMapping("hello-template")
//    public String practice(@RequestParam("name") String name, Model model)
//    {
//        model.addAttribute("name",name);
//        return "hello-template";
//    }
//
//    @GetMapping("hello") //url
//    public String hello(Model model)
//    {
//        model.addAttribute("data","hello!!"); // data = hello를 가지고
//        return "hello"; // resources - template - hello.html으로
//        /*
//            => resources:templates/ + (ViewName) + '.html'
//        */
//    }


}
