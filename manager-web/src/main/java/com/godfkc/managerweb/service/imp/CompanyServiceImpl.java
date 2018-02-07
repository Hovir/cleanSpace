package com.godfkc.managerweb.service.imp;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

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
        String url = centerUrl + "company/list";
        return this.restTemplate.postForObject(url, sentParameters, String.class);
    }

    @Override
    public String getCompaniesSearch(SentParameters sentParameters, String dateFrom, String dateTo, String companyName) {
        String url = centerUrl + "company/listSearch/" + dateFrom + "/" + dateTo + "/" + companyName;
        HashMap<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("dateFrom", dateFrom);
        mapSearch.put("dateTo", dateTo);
        mapSearch.put("companyName", companyName);
        //mapSearch.put("sentParameters",sentParameters);
        return this.restTemplate.postForObject(url, sentParameters, String.class);
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
    public String  updateCompanyOneDetails(Long id,String companyName,String companyUrl,String companyProfile){
        String url = centerUrl + "company/Update/"+id+"/"+companyName+"/"+companyUrl+"/"+companyProfile+"/edit";
        return this.restTemplate.getForObject(url, String.class);
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
}
