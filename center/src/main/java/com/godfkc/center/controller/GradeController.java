package com.godfkc.center.controller;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyVo;
import com.godfkc.center.entity.Level;
import com.godfkc.center.service.GradeService;
import com.godfkc.common.utils.JsonUtils;
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
    public boolean updataCommision(@PathVariable("commision") Long commision,@PathVariable("id") Long id){
        boolean b = gradeService.updataCommision(commision, id);
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
    public Level selectById(@PathVariable("id") Long id){
        Level level = gradeService.selectById(id);
        return level;
    }

}
