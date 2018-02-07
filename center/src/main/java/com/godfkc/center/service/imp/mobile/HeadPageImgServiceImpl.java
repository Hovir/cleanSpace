package com.godfkc.center.service.imp.mobile;

import com.godfkc.center.entity.HeadPageImg;
import com.godfkc.center.repository.HeadPageImgRepository;
import com.godfkc.center.service.mobile.HeadPageImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author syx
 * @date 17:54 2018-2-7
 * @description
 */
@Service
@Transactional
public class HeadPageImgServiceImpl implements HeadPageImgService{

    @Autowired
    private HeadPageImgRepository headPageImgRepository;

    @Override
    public List<HeadPageImg> selectHeadPageImg() {
        List<HeadPageImg> headPageImgList = headPageImgRepository.findByStatus(1);
        return headPageImgList;
    }
}
