package Passion.Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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


}