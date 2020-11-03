package Passion.Spring.controller;

import Passion.Spring.Form.KindForm;
import Passion.Spring.domain.Kind;
import Passion.Spring.service.AdminKindService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RequestMapping("admin/kind")
public class AdminKindController
{
    AdminKindService adminKindService;

    public AdminKindController(AdminKindService adminKindService) {
        this.adminKindService = adminKindService;
    }

    @GetMapping("list")

    public String kindList(Model model)
    {
        List<Kind> kinds = adminKindService.findKinds();
        model.addAttribute("kinds",kinds);
        return "admin/admin_kind_list";
    }

    @GetMapping("edit")
    public String kindEdit(@RequestParam("no") Long no, Model model)
    {
        Optional<Kind> kind = adminKindService.findByNo(no);
        model.addAttribute("kind",kind);
        return "admin/admin_kind_edit";
    }

    @GetMapping("view")
    public String kindView(@RequestParam("no") Long no, Model model)
    {
        Optional<Kind> kind = adminKindService.findByNo(no);
        model.addAttribute("kind",kind);

        return "admin/admin_kind_view";
    }

    @PostMapping("delete")
    @Transactional
    public String kindDelete(@RequestParam("no") Long no)
    {
        adminKindService.deleteByNo(no);
        return "redirect:list";
    }

    @PostMapping("update")
    public String kindUpdate(KindForm kindForm)
    {
        Kind kind = new Kind();
        kind = adminKindService.editFormKindObject(kind,kindForm);
        adminKindService.updateKind(kind);
        return "redirect:list";
    }




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
