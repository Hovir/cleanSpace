package com.godfkc.managerweb.controller;

import com.godfkc.common.pojo.manager.CardVo;
import com.godfkc.managerweb.service.CardService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/card/")
public class CardController {

    @Autowired
    private CardService cardService;

    @ResponseBody
    @RequestMapping(value = "allCompany", method = {RequestMethod.POST})
    public String allCompany() {
        return cardService.allCompany();
    }

    @ResponseBody
    @RequestMapping("insertCard/{id}/{num}")
    public void insertCard(@PathVariable("id") long id, @PathVariable("num") Integer num, HttpServletResponse response) {
        List<CardVo> cardVoList = cardService.insertCard(id, num);
        /*定义文件流*/
        OutputStream fOut = null;
        try {
            /*读取模板*/
            String filePath = "Card.xls";
            String conf = this.getClass().getClassLoader().getResource(filePath).getPath();
            /*读取模板*/
            FileInputStream fileInputStream = new FileInputStream(conf);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
            fileInputStream.close();
            /*读取第一个工种薄*/
            HSSFSheet xssfSheet = hssfWorkbook.getSheetAt(0);
            xssfSheet.setForceFormulaRecalculation(true);
            int i = 1;
            for (CardVo cardVo : cardVoList) {
                System.out.println(cardVo.getNum());
                /*卡状态*/
                String type = "";
                if (cardVo.getStatus() == 0) {
                    type = "停用";
                } else if (cardVo.getStatus() == 1) {
                    type = "未激活";
                } else if (cardVo.getStatus() == 2) {
                    type = "已激活";
                }
                HSSFCell cell0 = xssfSheet.createRow(i).createCell(0);
                cell0.setCellValue(cardVo.getNum());
                HSSFCell cell1 = xssfSheet.getRow(i).createCell(1);
                cell1.setCellValue(cardVo.getPassword());
                HSSFCell cell2 = xssfSheet.getRow(i).createCell(2);
                cell2.setCellValue(type);
                HSSFCell cell3 = xssfSheet.getRow(i).createCell(3);
                cell3.setCellValue(cardVo.getCreateTime());
                HSSFCell cell4 = xssfSheet.getRow(i).createCell(4);
                cell4.setCellValue(cardVo.getUpdateTime());
                HSSFCell cell5 = xssfSheet.getRow(i).createCell(5);
                cell5.setCellValue(cardVo.getCompanyName());
                ++i;
            }
            /*文件标题*/
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
            String codedFileName = java.net.URLEncoder.encode(simpleDateFormat.format(new Date()), "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");

            fOut = response.getOutputStream();
            hssfWorkbook.write(fOut);
            hssfWorkbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fOut != null) {
                    fOut.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
