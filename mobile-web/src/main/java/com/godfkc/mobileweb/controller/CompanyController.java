package com.godfkc.mobileweb.controller;

import com.godfkc.common.constant.PictureServerConstants;
import com.godfkc.common.utils.FastDFSClient;
import com.godfkc.common.utils.JsonUtils;
import com.godfkc.mobileweb.service.CompanyService;
import com.godfkc.mobileweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @param:
 * @return:
 * @author: Qi Zining
 * @date: 2018/2/1
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private OrderService orderService;
    @Value("${session.key.companyName}")
    private String companyNameSessionKey;
    @Value("${session.key.companyId}")
    private String companyIdSessionKey;
    @Value("${session.key.userName}")
    private String sessionkeyuserName;

    /**
     * 下边栏，点选企业
     *
     * @param:
     * @return:
     * @author: Qi Zining
     * @date: 2018/2/2
     */
    @RequestMapping("/myCompany")
    public ModelAndView turnToMyCompany(HttpServletRequest request) {
        String companyName = (String) request.getSession().getAttribute(companyNameSessionKey);
        System.out.println("/myCompany: name in session " + companyName);
        //
        ModelAndView companyPageTurn = null;
        if (null == companyName || companyName.trim().length() <= 0) {
            companyPageTurn = new ModelAndView("com-login");
        } else {//if exist companyName in session, turn to company index page;
            companyPageTurn = new ModelAndView("enterprise");
            String companyDetailByName = companyService.findCompanyDetailByName(companyName);
            System.out.println(companyDetailByName);
            /* set companyId into session*/
            Map<String, Object> stringDetailMap = JsonUtils.JsonToMap(companyDetailByName);
            Integer id = (Integer) stringDetailMap.get("id");
            request.getSession().setAttribute(companyIdSessionKey, id.longValue());

            companyPageTurn.addObject("company", companyDetailByName);
        }
        return companyPageTurn;
    }

    @Value("${session.key.companyId}")
    private String sessionKeyCompanyId;

    @RequestMapping("/loginAjax")
    @ResponseBody
    public boolean checkCompanyName(HttpServletRequest request, String comAccount, String comPwd) {
        boolean flag_login = companyService.findByNameAndPassword(comAccount, comPwd);
        if (flag_login) {
            request.getSession().setAttribute(companyNameSessionKey, comAccount);
        }
        return flag_login;
    }

    @RequestMapping("/showUnderCompany")
    @ResponseBody
    public Map<String, Object> showUnderCompany(HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        Map<String, Object> map = new HashMap<>();
        if (id != null) {
            map.put("flag", "1");
            String json = companyService.selectUnderCompany(id);
            map.put("companies", json);
        } else {
            map.put("flag", "2");
        }
        return map;
    }

    @RequestMapping("/uploadReport/{orderId}")
    @ResponseBody
    public Map<String, Object> uploadReport(HttpServletRequest request, MultipartFile[] file, @PathVariable Long orderId) throws Exception {
        //get company id from session
        Long companyId = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        //初始化返回值Map
        Map<String, Object> returnMap = new HashMap<>();

        //未登录或orderId 不存在
        if (null == companyId || null == orderId || orderId == 0) {
            returnMap.put("message", "noLogin");
            returnMap.put("reportUrl", "noUrl");
        } else {
            //获取order信息
            String orderInfo = orderService.findOrderById(orderId.toString());
            Map<String, Object> map = JsonUtils.JsonToMap(orderInfo);
            System.out.println("orderInfo+" + orderInfo);
            //获取上传文件的名字
            String fileName = file[0].getOriginalFilename();
            System.out.println(fileName + " uploaded Image's name~" + "orderId:" + orderId);
            //获取上传文件的后缀名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            //对后缀名大小写进行转换
            fileExt = fileExt.toLowerCase();
            //创建fastclien
            FastDFSClient fastDFSClient = null;
            try {
                //初始化图片服务器
                fastDFSClient = new FastDFSClient("fastDFS/fdfs_client.properties");
                //存在原图片，删除服务器上图片
                if (null != map.get("report")) {
                    System.out.println("have a report url and delete it from fastDFSClient!");
                    //删除原图片
                    fastDFSClient.deleteFile(((String) map.get("report")).substring(20));
                    returnMap.put("message", "There's data of report on database!");
                }
                 /*图片的类型过滤器*/
                //允许图片类型.jpg;.jpeg;.png;.bmp
                if (!"jpg".equals(fileExt) && !"jpeg".equals(fileExt) && !"png".equals(fileExt) && !"bmp".equals(fileExt)) {
                    returnMap.put("message", "noImage");
                    returnMap.put("reportUrl", "noUrl");
                } else {
                    //上传到fastDfs
                    String filePath = fastDFSClient.uploadFile(file[0].getBytes(), fileExt);
                    //插入数据库
                    boolean flag_updateReport = orderService.updateReport(orderId, companyId, PictureServerConstants._PICTURE_SERVER_URL + filePath);
                    System.out.println(PictureServerConstants._PICTURE_SERVER_URL + filePath);
                    if (flag_updateReport) {
                        returnMap.put("message", "success");
                        returnMap.put("reportUrl", PictureServerConstants._PICTURE_SERVER_URL + filePath);
                        returnMap.put("orderId", orderId);
                    } else {
                        fastDFSClient.deleteFile(filePath);
                        returnMap.put("message", "fail to update database!");
                    }
                }
            } catch (Exception e) {
                returnMap.put("message", "noImage");
                returnMap.put("reportUrl", "noUrl");
            }

        }
        return returnMap;
    }

    /**
     * 根据公司id判断是否绑定银行卡
     *
     * @param request
     * @return
     */
    @RequestMapping("/findBandCardByCompanyId")
    @ResponseBody
    public String findByCompanyId(HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        if (id != null) {
            String byCompanyId = companyService.findByCompanyId(id);
            if (byCompanyId == null) {//没有绑定银行卡
                return "2";
            }
            return byCompanyId;
        }
        return "1";
    }

    /**
     * 点击解绑
     */
    @RequestMapping("/unbindMod")
    @ResponseBody
    public String unbindMod(String id) {
        if (null != id) {
            Long bankCardId = Long.parseLong(id);
            boolean b = companyService.unbindMod(bankCardId);
            if (b) {
                return "success";
            } else {
                return "falied";
            }
        }
        return "falied";
    }

    /**
     * 查询所有银行
     */
    @RequestMapping("/findBankDictAll")
    @ResponseBody
    public String findBankDictAll() {
        return companyService.findBankDictAll();
    }


    @RequestMapping("/bindBankCard")
    @ResponseBody
    public String bindBankCard(String cardNo, String bankDictId, String phone, String username, HttpServletRequest request) {
        Long compayId = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        if (null != compayId) {
            String byCompanyId = companyService.findByCompanyId(compayId);
            if (byCompanyId != null) {
                return "4";
            }
            boolean b = companyService.bindBankCard(cardNo, phone, compayId, bankDictId, username);
            if (b) {
                return "2";
            } else {
                return "3";
            }

        }
        return "1";
    }

    @RequestMapping(value = "/getOrderListOfCompany")
    public String getOrderListOfCompany(HttpServletRequest request) {
        Long companyId = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        if (null != companyId) {
            String json_orderList = orderService.findAllOrderByCompanyId(companyId);
            System.out.println("jsonList: of company " +json_orderList);
            request.setAttribute("orderList", json_orderList);
            return "detection_and_management";
        } else {
            return "com-login";
        }
    }

}
