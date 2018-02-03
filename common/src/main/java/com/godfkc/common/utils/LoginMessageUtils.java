package com.godfkc.common.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class LoginMessageUtils {

    public static String getMessage(String phone,String identifyCode){
        PostMethod postMethod = new PostMethod("http://sms.dzd.com/v4/sms/send.do");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = format.format(new Date());// 时间戳
        String uid = "4471";// 账户id
        String key = "a871b74b0cace6cea87a830690eb19cf";// key

        System.out.println(identifyCode);
        System.out.println(phone);
        // 设置参数
        postMethod.addParameter("uid", uid);
        postMethod.addParameter("timestamp", timeStamp);
        postMethod.addParameter("sign", LoginMessageUtils.getSign(uid, key, timeStamp));
        postMethod.addParameter("mobile", phone);
        postMethod.addParameter("text", "【环保空间】：提示您，您的验证码为" + identifyCode + "，请您在60秒内使用。注意，打死也不能告诉别人哦，祝您购物愉快！");
        /*postMethod.addParameter("iid", "423");// 格子编码id*/
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        try {
            // post提交发送短信
            httpClient.executeMethod(postMethod);
            System.out.println(postMethod.getResponseBodyAsString());
            return postMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成签名
     *
     * @param account
     * @param key
     * @param timeStamp
     * @return
     */
    public static String getSign(String account, String key, String timeStamp) {
        // 账户 + key + 时间戳 MD5加密
        return string2MD5(account + key + timeStamp);
    }


    /**
     * MD5加密
     *
     * @param inStr
     * @return
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 生成6位随机验证码
     * @return
     */
    public static String get6code(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int ran = random.nextInt(10);
            sb.append(ran);
        }
        return sb.toString();
    }
}
