package com.godfkc.managerweb.service.imp;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@PropertySource("classpath:config.properties")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${center.url}")
    private String centerUrl;


    @Override
    public String getCompaniesList(SentParameters sentParameters) {
        ///company/list
        String url = centerUrl + "company/list";
        return this.restTemplate.postForObject(url, sentParameters, String.class);
    }

    @Override
    public String getCompaniesSearch(SentParameters sentParameters, String dateFrom, String dateTo, String companyName) {
        ///company/listSearch/{dateFrom}/{dateTo}/{companyName}
        String url = centerUrl + "company/listSearch/" + dateFrom + "/" + dateTo + "/" + companyName;
        //HashMap<String, Object> mapSearch = new HashMap<String, Object>();
        //mapSearch.put("dateFrom", dateFrom);
        //mapSearch.put("dateTo", dateTo);
        //mapSearch.put("companyName", companyName);
        //mapSearch.put("sentParameters",sentParameters);
        return this.restTemplate.postForObject(url, sentParameters, String.class);
    }
    @Override
    public String getCompanyNameList() {
        ///company/Add/companyNameList
        String url=centerUrl +"company/Add/companyNameList";
        return this.restTemplate.getForObject(url,String.class);
    }
    @Override
    public String getLevelNameList() {
        ///company/Add/levelNameList
        String url=centerUrl +"company/Add/levelNameList";
        return this.restTemplate.getForObject(url,String.class);
    }

    @Override
    public String getLevelsByCompanyIdAndLevelId(Long companyId){
        ///company/Add/{companyId}/levelNameList
        String url=centerUrl +"/company/Add/"+companyId+"/levelNameList";
        return this.restTemplate.getForObject(url,String.class);
    }


    @Override
    public String insertCompany(Map<String, Object> map,Long levelId,Long parentId) {
        ///company/Add/{levelId}/{parentId}/edit
        String url=centerUrl + "company/Add/"+levelId+"/"+parentId+"/edit";
        return  this.restTemplate.postForObject(url,map,String.class);
    }

/*
    @Override
    public String insertCompanyAddress(Map<String, Object> map,Long companyId) {
        ///company/Add/companyAddress/companyId/edit
        String url=centerUrl + "company/Add/companyAddress/"+companyId+"/edit";
        return  this.restTemplate.postForObject(url,map,String.class);
    }
*/

    @Override
    public String insertCompanyALL(Map<String, Object> mapCompany,Long levelId,Long parentId,String state,String city,String district) {
        ///company/Add/{levelId}/{parentId}/{state}/{city}/{district}/edit
        String url=centerUrl + "company/Add/"+levelId+"/"+parentId+"/"+state+"/"+city+"/"+district+"/edit";
        return  this.restTemplate.postForObject(url,mapCompany,String.class);
    }

    @Override
    public String getCompanyOneDetails(Long id) {
        ///company/details/{id}/edit
        String url = centerUrl + "company/details/{id}/edit";
        HashMap<String, Long> map = new HashMap<>();
        map.put("id", id);
        return this.restTemplate.getForObject(url, String.class, map);
    }

    @Override
    public String getCompanyAndAddress(Long companyId) {
        ///company/companyAddress/{companyId}/edit
        String url = centerUrl + "company/companyAddress/{companyId}/edit";
        HashMap<String, Long> map = new HashMap<>();
        map.put("companyId", companyId);
        return this.restTemplate.getForObject(url, String.class, map);
    }

    @Override
    public String  updateCompanyOneDetails(Map<String, Object> map){
        ///company/Update/update/edit
        String url = centerUrl + "company/Update/update/edit";
        map.put("updateTime",new Date());
        return this.restTemplate.postForObject(url,map, String.class);
    }

    @Override
    public String saveAndUpdateCompanyOneAdress(String state, String city, String district, Long companyId) {
        ///company/saveAndUpdate/adress/{companyId}/edit
        String url = centerUrl + "company/saveAndUpdate/adress/"+companyId+"/edit";
        Map<String, Object> map=new HashMap<>();
        map.put("state",state);
        map.put("city",city);
        map.put("district",district);
        return this.restTemplate.postForObject(url,map, String.class);
    }

    @Override
    public String updateCompanyOnePwd(Long id, String password) {
        // /company/Update/{id}/{password}/p/edit
        String url=centerUrl +"company/Update/"+id+"/"+password+"/p/edit";
        return this.restTemplate.getForObject(url, String.class);
    }

    @Override
    public String updateCompanyOneStatus(Long id, int status) {
        // /company/Update/{id}/{status}/s/edit
        String url=centerUrl +"company/Update/"+id+"/"+status+"/s/edit";
        return this.restTemplate.getForObject(url, String.class);
    }

    @Override
    public String getCompaniesByBn(String bn) {
        ///company/get/{bn}/edit
        String url=centerUrl +"company/get/"+bn+"/edit";
        return this.restTemplate.getForObject(url,String.class);
    }
}
