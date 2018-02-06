package com.godfkc.center.service.imp;

import com.godfkc.center.entity.HeadPageImg;
import com.godfkc.center.repository.HeadPageImgRepository;
import com.godfkc.center.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    @Autowired
    private HeadPageImgRepository headPageImgRepository;

    @Override
    public void webUploader(List<String> imgUrl) {
        List<HeadPageImg> headPageImgList = headPageImgRepository.findByStatus(1);
        for (HeadPageImg headPageImg : headPageImgList) {
            //删除原有图片
            headPageImgRepository.updateHeadImg(0, new Date(), headPageImg.getId());
        }
        //传入新图片
        for (String img : imgUrl) {
            HeadPageImg headPageImg = new HeadPageImg();
            headPageImg.setImgUrl(img);
            headPageImg.setCreateTime(new Date());
            headPageImg.setUpdateTime(new Date());
            headPageImg.setStatus(1);
            headPageImgRepository.save(headPageImg);
        }
    }
}
