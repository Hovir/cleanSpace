package com.godfkc.managerweb.controller;

import com.godfkc.managerweb.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @RequestMapping("selectGrade")
    @ResponseBody
    public String selectGrade(){
        String map = gradeService.findAll();
        return map;
    }



    @RequestMapping("selectById/{id}")
    public String selectById( Model model, @PathVariable("id")Long id){
        Map<String, Object> map = gradeService.selectById(id);
        System.out.println("-----------------------------------");
        System.out.println(map.get("commision"));
        System.out.println("-------------------------------------------");
        model.addAttribute("map",map);
        return "examine/updataData";
    }

    /*
    * 修改佣金
    * */
    @RequestMapping("updataCommision")
    public String updataCommision( Long commision,Long id){
        gradeService.updataCommision(commision,id);
        System.out.println("===================");
        return "/admin/index";
    }
}
