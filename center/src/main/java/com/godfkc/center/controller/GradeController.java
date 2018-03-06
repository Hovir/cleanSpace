package com.godfkc.center.controller;

import com.godfkc.center.entity.Level;
import com.godfkc.center.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @RequestMapping("/selectGrade")
    public List<Level> selectGrade(){
        List<Level> companyList = gradeService.findAll();
        if(companyList!=null||companyList.size()>0){
            System.out.println(companyList);
            return companyList;
        }else {
            return null;
        }
    }
    /*
    * 根据id修改佣金
    * */
    @RequestMapping("/updataCommision/{commision}/{id}")
    public boolean updataCommision(@PathVariable("commision") String commision,@PathVariable("id") String id){
        String s1 = commision.replaceAll(",", "");
        long l1 = Long.parseLong(s1);
        String s = id.replaceAll(",", "");
        long l = Long.parseLong(s);
        boolean b = gradeService.updataCommision(l1, l);
        if(b==true){
            return true;
        }else{
            return false;
        }
    }

    /*
    * 根据id查询数据
    * */
    @RequestMapping("/selectById/{id}")
    public Level selectById(@PathVariable("id") String id){
        String s = id.replaceAll(",", "");
        long l = Long.parseLong(s);
        Level level = gradeService.selectById(l);
        return level;
    }

}
