package Passion.Spring.controller;

import Passion.Spring.domain.Disease;
import Passion.Spring.service.AdminDiseaseService;
import Passion.Spring.tech.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("disease")
public class DiseaseController
{
    AdminDiseaseService adminDiseaseService;
    public DiseaseController(AdminDiseaseService adminDiseaseService)
    {
        this.adminDiseaseService = adminDiseaseService;
    }
    @GetMapping("list")
    public String diseaseList(HttpSession session, @RequestParam ("no") int no, Model model)
    {


        List <Disease> diseases = adminDiseaseService.findDiseases();
        List <Disease> searchedDiseases = new ArrayList<>();
        List <Disease> integration = new ArrayList<>();
        int diseasesCount;
        String searchText = (String)session.getAttribute("searchText");
        session.removeAttribute("searchText"); //세션부터 삭제하시고
        session.removeAttribute("searchKind");
        if ( searchText != null) // 찾고자 하는 내용이 있다면
        {
            for ( Disease disease : diseases)
            {
                if(disease.getTitle().contains(searchText) == true)
                    searchedDiseases.add(disease);
            }
            diseasesCount = searchedDiseases.size();
            integration = searchedDiseases;
        }
        else
        {
            diseasesCount = diseases.size();
            integration = diseases;
        }
        Pagination <Disease> pagination = new Pagination<Disease>(integration,no,integration.size(),5,5);
        List<Disease> help = pagination.paginationObject();
        List<Integer> pageNum = pagination.paginationPage();

        model.addAttribute("diseases",help);
        model.addAttribute("maxPageNum",pageNum.size());
        model.addAttribute("diseasesCount",integration.size());
        model.addAttribute("pageNum",pageNum);

        return "main/disease_list";
    }
    @GetMapping("view")
    public String diseaseView(@RequestParam("no") Long no, Model model)
    {
        Optional <Disease> disease = adminDiseaseService.findByNo(no);
        model.addAttribute("disease",disease.get());
        return "main/disease_view";
    }

}
