package com.godfkc.center.service.imp;

import com.godfkc.center.entity.Card;
import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.Level;
import com.godfkc.center.repository.CardRepository;
import com.godfkc.center.repository.CompanyRepository;
import com.godfkc.center.repository.LevelRepository;
import com.godfkc.center.service.CardService;
import com.godfkc.common.pojo.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Ztree> selectLevel() {
        List<Ztree> ztreeList = new ArrayList<>();
        List<Level> levelList = levelRepository.findAll();
        List<Company> companyList = companyRepository.findAll();

        for (Level level : levelList) {
            Ztree ztree = new Ztree();
            ztree.setId(level.getId());
            ztree.setpId(0);
            ztree.setName(level.getName());
            ztree.setNocheck(true);
            ztreeList.add(ztree);
        }
        for (Company company : companyList) {
            Ztree ztree = new Ztree();
            ztree.setId(company.getId());
            ztree.setpId(company.getLevel().getId());
            ztree.setName(company.getName());
            ztreeList.add(ztree);
        }
        return ztreeList;
    }

    @Override
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public List<Card> insertCard(long id, Integer num) {
        List<Card> list = new ArrayList<>();
        //根据id查询公司信息
        Company company = companyRepository.findById(id);
        //生成卡号和密码
        for (int i = 0; i < num; i++) {
            Card card = new Card();
            card.setCreateTime(new Date());
            card.setUpdateTime(new Date());
            card.setStatus(1);
            card.setCompany(company);
            String time = String.valueOf(Calendar.getInstance().getTime().getTime());
            StringBuilder data = new StringBuilder(time.substring(time.length() - 5, time.length()));
            StringBuilder intFlag = new StringBuilder(String.valueOf((int) ((Math.random() * 9 + 1) * 10000)));
            StringBuilder bn = new StringBuilder(company.getBn());
            String n = bn.append(data).append(intFlag).toString();
            String p = n.substring(6, 12);
            card.setNum(n);
            card.setPassword(p);
            list.add(card);
            cardRepository.save(card);
        }
        return list;
    }
}
