package com.godfkc.managerweb.controller;

import com.godfkc.common.constant.PictureServerConstants;
import com.godfkc.common.utils.FastDFSClient;
import com.godfkc.managerweb.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/picture/")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @ResponseBody
    @RequestMapping(value = "webUploader", method = {RequestMethod.POST})
    public void webUploader(@RequestParam("files") MultipartFile[] files) {
        List<String> imgUrl = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                //获取上传文件的名字
                String fileName = file.getOriginalFilename();
                //获取上传文件的后缀名
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                //对后缀名大小写进行转换
                fileExt = fileExt.toLowerCase();
                //创建fastclien
                FastDFSClient fastDFSClient = new FastDFSClient("fastDFS.properties");
                //上传到fastDfs
                String urlName = fastDFSClient.uploadFile(file.getBytes(), fileExt);
                String filePath = PictureServerConstants._PICTURE_SERVER_URL + urlName;
                imgUrl.add(filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pictureService.webUploader(imgUrl);
    }
}
