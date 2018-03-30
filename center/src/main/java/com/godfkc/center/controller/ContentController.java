package com.godfkc.center.controller;

import com.godfkc.center.entity.Content;
import com.godfkc.center.service.ContentService;
import com.godfkc.common.pojo.dataTables.DataTablesReturn;
import com.godfkc.common.pojo.dataTables.ReturnedData;
import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.common.utils.dataTables.ReturnedDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/content/")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("contentList")
    public ReturnedData selectList(@RequestBody SentParameters sentParameters) {
        //返回到前台的数据
        ReturnedData<Content> returnedData = new ReturnedData<>();
        DataTablesReturn dataTablesReturn = new ReturnedDataUtil().DataRequestRequest(sentParameters);

        Page<Content> page = contentService.selectList("1", dataTablesReturn.getPage(), dataTablesReturn.getSize(), dataTablesReturn.getDir(), dataTablesReturn.getData(), dataTablesReturn.getSearch());
        //返回到前台的数据
        returnedData.setDraw(sentParameters.getDraw());
        returnedData.setRecordsTotal((int) page.getTotalElements());
        returnedData.setRecordsFiltered((int) page.getTotalElements());
        returnedData.setData(page.getContent());

        return returnedData;
    }

    @RequestMapping("deleteContent")
    public Boolean deleteContent(@RequestBody List<Long> id) {
        for (Long newsIs : id) {
            contentService.deleteContent(newsIs, "0");
        }
        return true;
    }

    /**
     * @Author:lhy
     * @Data:2018/2/2 11:02
     * @Version:V1.0
     * @Des:添加资讯
     */
    @RequestMapping(value = "insertContent", method = {RequestMethod.POST})
    public void insertContent(@RequestBody Content content) {
        contentService.insertContent(content);
    }

    /**
     * @Author:lhy
     * @Data:2018/2/9 15:35
     * @Version:V1.0
     * @Des:根据id查询单条资讯
     */
    @RequestMapping("selectUpdateContent/{id}")
    public Content selectUpdateContent(@PathVariable("id") long id) {
        return this.contentService.selectContent(id);
    }

    /**
     * @Author:lhy
     * @Data:2018/2/9 15:52
     * @Version:V1.0
     * @Des:保存修改后的资讯
     */
    @RequestMapping("saveUpdateContent")
    private void saveUpdateContent(@RequestBody Content content) {
        this.contentService.saveUpdateContent(content);
    }


    @GetMapping("saveB")
    public String saveB(){
        return this.contentService.saveB();
    }


}
