package com.godfkc.mobileweb.controller;

import com.godfkc.common.pojo.mobile.WxResult;
import com.godfkc.common.utils.IPUtils;
import com.godfkc.common.utils.JsonUtils;
import com.godfkc.common.utils.MD5Util;
import com.godfkc.mobileweb.service.CardService;
import com.godfkc.mobileweb.service.CompanyService;
import com.godfkc.mobileweb.service.LevelService;
import com.godfkc.mobileweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2017/12/19
 * @description
 */
@Controller
@RequestMapping("/wx")
@PropertySource("classpath:config.properties")
public class WxPayController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;

    @Autowired
    private LevelService levelService;


    @Value("${wx.b_id}")
    private String bId;

    @Value("${wx.bm_id}")
    private String bmId;

    @Value("${wx.key}")
    private String key;

    @Value("${wx.notify_url}")
    private String notifuUrl;

    @Value("${wx.type}")
    private String type;

    @Value("${wx.name}")
    private String name;

    @Value("${wx.info}")
    private String info;

    @Value("${wx.order_url}")
    private String orderUrl;

    @Value("${wx.check_url}")
    private String checkUrl;

    @Value("${wx.redirect_url}")
    private String redirectUrl;

    @Value("${wx.sign}")
    private String sign;

    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(WxPayController.class);

    //生成签名
    //final String sign = MD5Util.encode(key + bId + bmId);

    /**
     * 下单接口
     *
     * @param
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/makeOrder", method = RequestMethod.POST)
    public String makeOrder(Model model, String cardId,String phone, String price, HttpServletRequest request) throws IOException, GeneralSecurityException {


        logger.info("makeOrder");

//        String newOrderId = orderId + System.currentTimeMillis();
        String orderId=cardId+"_"+phone;
        System.out.println(cardId+"-----"+phone+"----"+price+"--------"+orderId);
        //封装json
        Map<String, String> parameters = new HashMap<>();
        parameters.put("b_id", bId);
        parameters.put("bm_id", bmId);
        parameters.put("price", price);
        parameters.put("order_no", orderId);
        parameters.put("notify_url", notifuUrl);
        parameters.put("spbill_create_ip", IPUtils.getIpAddress(request));
        parameters.put("type", type);
        parameters.put("name", name);
        parameters.put("info", info);
        parameters.put("sign", sign);

        String wxResult1 = this.restTemplate.postForObject(orderUrl, parameters, String.class);
        WxResult wxResult = JsonUtils.Json2Object(wxResult1, WxResult.class);
        logger.info("请求参数：" + parameters.toString());

        if ("success".equals(wxResult.getStatus())) {

            logger.info("下单成功");
            logger.info("返回参数：" + wxResult.toString());

            String payUrl = wxResult.getPayUrl();
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            payUrl = payUrl + "&redirect_url=" + redirectUrl + "/" + wxResult.getS_no();
            return "redirect:" + payUrl;
        }
        logger.info("失败");
        model.addAttribute("msg", wxResult.getMsg());
        model.addAttribute("cardId",cardId);
        model.addAttribute("phone", phone);
        return "pay";
    }


    /**
     * 跳转页面
     *
     * @param s_no
     * @param model
     * @return
     */
    @RequestMapping(value = "/redirect_url/{s_no}", method = RequestMethod.GET)
    public String redirectUrl(@PathVariable("s_no") String s_no, Model model) throws UnsupportedEncodingException {
        logger.info("checkPay");

        model.addAttribute("b_id", bId);
        model.addAttribute("bm_id", bmId);
        model.addAttribute("s_no", s_no);
        model.addAttribute("sign", sign);

        return "checkPay";
    }

    /**
     * 查询接口
     *
     * @param s_no
     * @return
     */
    @RequestMapping(value = "/checkPay", method = RequestMethod.POST)
    public String checkPay(String s_no) throws GeneralSecurityException {
        //封装json
        Map<String, String> parameters = new HashMap<>();
        parameters.put("b_id", bId);
        parameters.put("bm_id", bmId);
        parameters.put("s_no", s_no);
        parameters.put("sign", sign);

        WxResult wxResult = this.restTemplate.postForObject(checkUrl, parameters, WxResult.class);

        logger.info("请求参数：" + parameters.toString());
        if ("success".equals(wxResult.getStatus())) {
            logger.info("查询成功");
            logger.info("返回参数：" + wxResult.toString());

            return "paySucceed";
        }

        return "/mobile/payError";
    }


    /**
     * 回调数据
     *
     * @param status
     * @param order_no
     * @param s_no
     * @param order_price
     * @param order_time
     * @return
     */
    @RequestMapping(value = "/notify_url", method = RequestMethod.POST)
    @ResponseBody
    public String notifyUrl(@RequestParam("status") String status, @RequestParam("order_no") String order_no, @RequestParam("s_no") String s_no,
                            @RequestParam("order_price") String order_price, @RequestParam("order_time") String order_time) {

        if ("success".equals(status)) {
            logger.info("支付成功");

            /*
            * 成功后代码
            */
            String[] split = order_no.split("_");
            String cardId=split[0];
            String phone=split[1];
            Long cardId1=Long.parseLong(cardId);
            Long userId=userService.selectUserIdByPhone(phone);
            Long companyId = cardService.selectCompanyIdByCard(cardId1);
            String json = companyService.findCompanyFundsByCompanyId(companyId);
            Map<String, Object> map = JsonUtils.JsonToMap(json);
            int bMoney = (Integer) map.get("money");
            Long levelId = companyService.selectLevelIdByCompanyId(companyId);
            Long commision = levelService.selectCommisionByLevelId(levelId);
            logger.info(cardId+"------"+levelId+"----------"+commision.intValue()*100);
            int funds = bMoney + commision.intValue()*100;
            int commision1=commision.intValue()*100;
            //改变余额
            boolean chagBal = companyService.changeBalance(funds, companyId);
            if(chagBal){
                logger.info("账号余额改变成功");
            }else {
                logger.info("账号余额改变失败");
            }
            boolean fundsLog = companyService.insertFundsLog(funds, commision1+"",companyId,cardId,2);
            if(fundsLog){
                logger.info("日志表添加成功");
            }else {
                logger.info("日志表添加失败");
            }
            boolean b = cardService.updateUserIdAndStatus(userId,cardId1);
            if(b){
                logger.info("修改card status及userId成功");
            }else {
                logger.info("修改card status及userId失败");
            }
            return "success";
        } else {
            return "";
        }
    }


}
