package com.godfkc.mobileweb.controller;

import com.godfkc.common.pojo.mobile.WxResult;
import com.godfkc.common.utils.IPUtils;
import com.godfkc.common.utils.MD5Util;
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

    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(WxPayController.class);

    //生成签名
    final String sign = MD5Util.encode(key + bId + bmId);

    /**
     * 下单接口
     *
     * @param orderId
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/makeOrder", method = RequestMethod.POST)
    public String makeOrder(Model model, String orderId, String price, HttpServletRequest request) throws IOException, GeneralSecurityException {


        logger.info("makeOrder");

//        String newOrderId = orderId + System.currentTimeMillis();

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

        WxResult wxResult = this.restTemplate.postForObject(orderUrl, parameters, WxResult.class);

        logger.info("请求参数：" + parameters.toString());

        if ("success".equals(wxResult.getStatus())) {

            logger.info("下单成功");
            logger.info("返回参数：" + wxResult.toString());

            String payUrl = wxResult.getPayUrl();
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            payUrl = payUrl + "&redirect_url=" + redirectUrl + "/" + wxResult.getS_no();
            return "redirect:" + payUrl;
        }

        model.addAttribute("msg", wxResult.getMsg());
        return "/mobile/order/mobilePaymentPage";
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

            return "/mobile/payOk";
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

            return "success";
        } else {
            return "";
        }
    }


}
