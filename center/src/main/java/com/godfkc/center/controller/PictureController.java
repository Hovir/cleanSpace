package com.godfkc.center.controller;

import com.godfkc.center.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/picture/")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "webUploader", method = {RequestMethod.POST})
    public void webUploader(@RequestBody List<String> imgUrl) {
        pictureService.webUploader(imgUrl);
    }
}
