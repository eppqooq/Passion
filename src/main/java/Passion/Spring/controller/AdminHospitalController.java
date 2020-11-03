

package Passion.Spring.controller;

import Passion.Spring.Form.HospitalForm;
import Passion.Spring.domain.Hospital;
import Passion.Spring.service.AdminHospitalService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;




@RequestMapping("admin/hospital") // default 경로 설정
public class AdminHospitalController
{
    AdminHospitalService adminHospitalService;

    public AdminHospitalController(AdminHospitalService adminHospitalService) {
        this.adminHospitalService = adminHospitalService;
    }

    @GetMapping("list1")
    public String hospitalList(Model model)
    {
        List<Hospital> hospitals = adminHospitalService.findHospitals();
        model.addAttribute("hospitals",hospitals);
        return "admin/admin_hospital_list";
    }

    @GetMapping("edit")
    public String hospitalEdit(@RequestParam("no") Long no, Model model)
    {
        Optional<Hospital> hospital = adminHospitalService.findByNo(no);
        model.addAttribute("hospital",hospital);
        return "admin/admin_hospital_edit";
    }


    @PostMapping("update")
    public String hospitalUpdate(HospitalForm hospitalForm)
    {
        Hospital hospital = new Hospital();
        hospital=adminHospitalService.editFormHospitalObject(hospital,hospitalForm);
        adminHospitalService.updateHospital(hospital);
        return "redirect:list";
    }

    @GetMapping("view1")
    public String hospitalView(@RequestParam("no") Long no, Model model)
    {
        Optional<Hospital> hospital = adminHospitalService.findByNo(no);
        model.addAttribute("hospital",hospital);

        return "admin/admin_hospital_view";
    }

    @PostMapping("delete")
    @Transactional
    public String hospitalDelete(@RequestParam("no") Long no)
    {
        adminHospitalService.deleteByNo(no);
        return "redirect:list";
    }

//    @PatchMapping("update")
//    public String hospitalPatch (HospitalForm form)
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
