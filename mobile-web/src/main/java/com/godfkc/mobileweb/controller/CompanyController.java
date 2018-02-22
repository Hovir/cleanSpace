package com.godfkc.mobileweb.controller;

import com.godfkc.common.constant.PictureServerConstants;
import com.godfkc.common.utils.FastDFSClient;
import com.godfkc.common.utils.JsonUtils;
import com.godfkc.mobileweb.service.CompanyService;
import com.godfkc.mobileweb.service.OrderService;
import org.apache.commons.codec.digest.DigestUtils;
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
    @Value("${session.key.companyImg}")
    private String sessionkeycompanyImg;

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
        ModelAndView companyPageTurn = null;
        if (null == companyName || companyName.trim().length() <= 0) {
            companyPageTurn = new ModelAndView("com-login");
        } else {//if exist companyName in session, turn to company index page;
            companyPageTurn = new ModelAndView("enterprise");
            String companyDetailByName = companyService.findCompanyDetailByName(companyName);
            System.out.println("企业登录返回json:" + companyDetailByName);
            /* set companyId into session*/
            Map<String, Object> stringDetailMap = JsonUtils.JsonToMap(companyDetailByName);
            Integer id = (Integer) stringDetailMap.get("id");
            request.getSession().setAttribute(companyIdSessionKey, id.longValue());
            String companyImg = (String) stringDetailMap.get("companyImg");
            request.getSession().setAttribute(sessionkeycompanyImg, companyImg);
            companyPageTurn.addObject("company", companyDetailByName);
        }
        return companyPageTurn;
    }

    @Value("${session.key.companyId}")
    private String sessionKeyCompanyId;

    @RequestMapping("/loginAjax")
    @ResponseBody
    public boolean checkCompanyName(HttpServletRequest request, String comAccount, String comPwd) {
        boolean flag_login = companyService.findByNameAndPassword(comAccount, DigestUtils.md5Hex(comPwd));
        if (flag_login) {//成功登陆，session存入公司名称
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

    /**
     * 绑定银行卡
     *
     * @param cardNo
     * @param bankDictId
     * @param phone
     * @param username
     * @param request
     * @return
     */
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



    //bussiness多条件查询商家
    @RequestMapping("/selectCompanies")
    @ResponseBody
    public String selectCompanies(Long levelId,String state,String city,String district){
        String json=companyService.selectCompanies(levelId,state,city,district);
        return json;
    }

    /**
     * 点击现金提现
     *
     * @param request
     * @param money
     * @return
     */
    @RequestMapping("/moneyPage/{money}")
    public String toMoney(HttpServletRequest request, @PathVariable String money) {
        Long companyId = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        String companyImg = (String) request.getSession().getAttribute(sessionkeycompanyImg);
        if (null != companyId && null != companyImg) {
            request.setAttribute("money", money);
            request.setAttribute("companyImg", companyImg);
            return "money";
        } else {
            return "com-login";
        }
    }

    /**
     * 确认提现
     * @param withdrawlMoney
     * @param request
     * @return
     */
    @RequestMapping("/confirmWithdrawals")
    @ResponseBody
    public String confirmWithdrawals(String withdrawlMoney,HttpServletRequest request){
        Long companyId = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        System.out.println("要提现的金额："+withdrawlMoney);
        if (companyId != null){
            String companyFundsByCompanyId = companyService.findCompanyFundsByCompanyId(companyId);
            System.out.println("返回的银行资金信息："+companyFundsByCompanyId);
            Map<String, Object> map1 = JsonUtils.JsonToMap(companyFundsByCompanyId);
            int bMoney = (Integer) map1.get("money");
            System.out.println("数据库查出的余额："+bMoney);
            int withdraw = Integer.parseInt(withdrawlMoney);
            if (bMoney < withdraw){
                System.out.println("余额不足");
                return "2";
            }else {
                int afterWithdrawInt = bMoney - withdraw;
                System.out.println("提现后的余额："+ afterWithdrawInt);
                boolean chagBal = companyService.changeBalance(afterWithdrawInt, companyId);
                if(!chagBal){
                    System.out.println("提现改变余额失败");
                    return "3";
                }else {
                    System.out.println("提现改变余额成功");
                    //插入提现表
                    boolean insertFunwith = companyService.insertFundsWithdraw(withdrawlMoney, companyId);
                   if (!insertFunwith){
                       System.out.println("插入提现表失败");
                       return "3";
                   }else {
                       System.out.println("插入提现表成功");
                       //插入业务日志表
                       String descreption = null;
                       boolean fundsLog = companyService.insertFundsLog(afterWithdrawInt, withdrawlMoney, companyId,descreption,1);
                        if (!fundsLog){
                            System.out.println("插入业务日志表失败");
                            return "3";
                        }else {
                            System.out.println("插入业务日志表成功");
                            return "4";
                        }
                   }
                }
            }
        }else {
            System.out.println("连接超时");
            return "1";
        }
    }

    @RequestMapping("/detailed")
    @ResponseBody
    public String detailedMethod(HttpServletRequest request){
        Long companyId = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        if (companyId != null){
            String fundsLogList = companyService.findFundsLogByCompanyId(companyId);
            return fundsLogList;
        }else {
            System.out.println("连接超时");
            return "1";
        }
    }
}
