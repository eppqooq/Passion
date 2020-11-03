package Passion.Spring.controller;

import Passion.Spring.Form.DiseaseForm;
import Passion.Spring.domain.Disease;
import Passion.Spring.service.AdminDiseaseService;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("admin/disease")
public class AdminDiseaseController {
    AdminDiseaseService adminDiseaseService;
    public AdminDiseaseController(AdminDiseaseService adminDiseaseService) {
        this.adminDiseaseService = adminDiseaseService;
    }
    @GetMapping("list")
    public String diseaseList(Model model)
    {
        List<Disease> diseases = adminDiseaseService.findDiseases();

        model.addAttribute("diseases",diseases);
        return "admin/admin_disease_list";
    }


    @GetMapping("view")
    public String diseaseView(@RequestParam Long no, Model model)
    {
        Optional<Disease> disease = adminDiseaseService.findByNo(no);
        model.addAttribute("disease",disease);
        return "admin/admin_disease_view";
    }
    @GetMapping("edit")
    public String diseaseEdit(@RequestParam Long no, Model model)
    {
        Optional<Disease> disease = adminDiseaseService.findByNo(no);
        model.addAttribute("disease",disease);
        return "admin/admin_disease_edit";
    }

    @PostMapping("update")
    public String diseaseUpdate(DiseaseForm diseaseForm)
    {
        Disease disease = new Disease();
        disease = adminDiseaseService.editFormDiseaseObject(disease,diseaseForm);
        adminDiseaseService.updateDisease(disease);
        return "redirect:list";
    }

    @PostMapping("delete")
    @Transactional
    public String memberDelete(@RequestParam("no") Long no)
    {
        adminDiseaseService.deleteByNo(no);
        return "redirect:list";
    }
}
