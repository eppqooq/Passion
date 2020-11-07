package Passion.Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.lang.model.SourceVersion;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @GetMapping("/")
    public String main()
    {
        return "main/index";
    }
    @GetMapping("admin")
    public String admin_main()
    {
        return "admin/admin_main"; // template/admin/admin_main + .html
    }
    @PostMapping("search")
    public String Search(@RequestParam("searchKind") String searchKind,
                         @RequestParam("searchText") String searchText,
                         HttpSession session, Model model)
    {
        session.setAttribute("searchText",searchText);
        session.setAttribute("searchKind",searchKind);
        //model.addAttribute("searchText",searchText);
        if(searchKind.equals("게시판"))           // searchText가 게시판이면
        {
            return "redirect:/board/list?no=1";
        }
        else if(searchKind.equals("병원")) // searchText가 병원이면
            {
                System.out.println("병원");
                return "redirect:/hospital/list?no=1";
            }
            else  // searchText 가 질병이면
            {
                System.out.println("질병");
                return "redirect:/disease/list?no=1";
            }
    }

}