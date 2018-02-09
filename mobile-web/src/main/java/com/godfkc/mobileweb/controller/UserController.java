package com.godfkc.mobileweb.controller;

import com.godfkc.common.constant.PictureServerConstants;
import com.godfkc.common.utils.FastDFSClient;
import com.godfkc.common.utils.JsonUtils;
import com.godfkc.common.utils.LoginMessageUtils;
import com.godfkc.mobileweb.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wj
 * @date 9:40 2018/1/31
 * @description 用户相关
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Value("${session.key.userName}")
    private String sessionKeyUserName;

    @Value("${session.key.userPhone}")
    private String sessionKeyUserPhone;

    @Value("${session.key.userPwd}")
    private String sessionKeyUserPwd;

    @Value("${session.key.userHeadImg}")
    private String sessionKeyUserHeadImg;

    //用户登陆
    @RequestMapping("/doLogin")
    @ResponseBody
    public boolean doLogin(String phone, String password, HttpServletRequest request) {
        String makePassword = DigestUtils.md5Hex(password);
        String json = userService.doLogin(phone, makePassword);
        HttpSession httpSession = request.getSession();
        if (json != null && json.length() > 0) {
            Map<String, Object> map = JsonUtils.JsonToMap(json);
            httpSession.setAttribute(sessionKeyUserName, map.get("name"));
            httpSession.setAttribute(sessionKeyUserPhone, phone);
            httpSession.setAttribute(sessionKeyUserPwd, password);
            httpSession.setAttribute(sessionKeyUserHeadImg, map.get("headImg"));
            return true;
        } else {
            return false;
        }
    }

    //注册点击获取验证码
    @RequestMapping("/isExist")
    @ResponseBody
    public String isExist(String phone) {
        Long userId = userService.selectUserIdByPhone(phone);
        if (userId != null) {
            //手机号已注册
            return "1";
        } else {
            return "2";
        }
    }

    /**
     * 点击注册
     */
    @RequestMapping(value = "/userRegist", method = RequestMethod.POST)
    @ResponseBody
    public String userRegist(String phone, String verfiy, String pwd) throws Exception {
        Long userId = userService.selectUserIdByPhone(phone);
        if (userId != null) {
            System.out.println("注册日志：手机号已注册!");
            return "1";
        }
        String rightCode = (String) redisTemplate.opsForValue().get(phone);
        if (rightCode == null || !rightCode.equals(verfiy)) {
            System.out.println("注册日志：验证码不正确!");
            return "2";
        }
        String password = DigestUtils.md5Hex(pwd);
        boolean sign = userService.insertUserInfo(phone, password);
        if (sign) {
            System.out.println("注册日志：注册成功!");
            return "3";
        } else {
            System.out.println("注册日志：注册失败!");
            return "4";
        }
    }

    /**
     * 发送注册短信
     */
    @RequestMapping(value = "/getRegisterMessage", method = RequestMethod.POST)
    @ResponseBody
    public String getRegisterMessage(@RequestParam(value = "phone") String phone) throws Exception {
        //生成验证码
        String identifyCode = LoginMessageUtils.get6code();
        //把验证码保存到redis
        redisTemplate.opsForValue().set(phone, identifyCode, 300, TimeUnit.SECONDS);
        //发送短信
        return LoginMessageUtils.getMessage(phone, identifyCode);
    }


    @RequestMapping("/confirmLogin")
    @ResponseBody
    public String confirmLogin(String phone, String verfiy, String password, HttpServletRequest request) {
        Long userId = userService.selectUserIdByPhone(phone);
        if (userId == null) {
            System.out.println("重置密码日志：手机号未注册!");
            return "1";
        }

        String rightCode = (String) redisTemplate.opsForValue().get(phone);
        if (rightCode == null || !rightCode.equals(verfiy)) {
            System.out.println("重置密码日志：验证码不正确/失效!");
            return "2";
        }
        String pwd = DigestUtils.md5Hex(password);
        boolean sign = userService.updatePwdByPhone(phone, pwd);
        if (sign) {
            System.out.println("重置密码日志：修改成功!");
            HttpSession httpSession = request.getSession();
            String json = userService.doLogin(phone, pwd);
            Map<String, Object> map = JsonUtils.JsonToMap(json);
            httpSession.setAttribute(sessionKeyUserName, map.get("name"));
            httpSession.setAttribute(sessionKeyUserPhone, phone);
            httpSession.setAttribute(sessionKeyUserPwd, password);
            httpSession.setAttribute(sessionKeyUserHeadImg, map.get("headImg"));
            return "3";
        } else {
            System.out.println("重置密码日志：修改失败!");
            return "4";
        }
    }

    //密码管理修改密码
    @RequestMapping("/changeUserPwd")
    @ResponseBody
    public String changeUserPwd(String oldPassword, String password, HttpServletRequest request) {
        String phone = (String) request.getSession().getAttribute(sessionKeyUserPhone);
        if (phone != null && phone.length() > 0) {
            String pwd = DigestUtils.md5Hex(password);
            String oldPwd = DigestUtils.md5Hex(oldPassword);
            String json = userService.doLogin(phone, oldPwd);
            if (json == null || json.length() == 0) {
                return "4";
            }
            boolean sign = userService.updatePwdByPhone(phone, pwd);
            if (sign) {
                //修改成功
                HttpSession httpSession = request.getSession();
                Map<String, Object> map = JsonUtils.JsonToMap(json);
                httpSession.setAttribute(sessionKeyUserName, map.get("name"));
                httpSession.setAttribute(sessionKeyUserPhone, phone);
                httpSession.setAttribute(sessionKeyUserPwd, password);
                httpSession.setAttribute(sessionKeyUserHeadImg, map.get("headImg"));
                return "1";
            } else {
                //修改失败
                return "2";
            }
        } else {
            //登陆超时
            return "3";
        }
    }


    @RequestMapping("/exitLogon")
    public String exitLogon(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute(sessionKeyUserName);
        httpSession.removeAttribute(sessionKeyUserPhone);
        httpSession.removeAttribute(sessionKeyUserPwd);
        httpSession.removeAttribute(sessionKeyUserHeadImg);
        return "index";
    }

    //修改头像
    @RequestMapping(value = "userUpdateImageFinish", method = RequestMethod.POST)
    @ResponseBody
    public String updateImageFinish(String imgStr, HttpServletRequest request) {
        String userPhone = (String) request.getSession().getAttribute(sessionKeyUserPhone);
        if (userPhone != null) {
            Long userId = userService.selectUserIdByPhone(userPhone);
            //判断是否上传新头像图片
            if (imgStr != null && imgStr != "") {
                BASE64Decoder decoder = new BASE64Decoder();
                String[] arr = imgStr.split(",");
                //取得文件类型
                String imgType = arr[0].substring(arr[0].indexOf("/") + 1, arr[0].indexOf(";"));
                //取得密文
                imgStr = arr[1];
                //Base64解码
                byte[] b = new byte[0];
                try {
                    b = decoder.decodeBuffer(imgStr);
                    for (int i = 0; i < b.length; ++i) {
                        if (b[i] < 0) {
                            b[i] += 256;
                        }
                    }
                    //上传图片
                    FastDFSClient fastDFSClient = new FastDFSClient("fastDFS/fdfs_client.properties");
                    String imagePath1 = fastDFSClient.uploadFile(b, imgType);
                    //更改数据库图片
                    String imagePath = PictureServerConstants._PICTURE_SERVER_URL + imagePath1;
                    System.out.println("图片路径："+imagePath);
                    boolean updateImg = userService.updateUserImgById(imagePath, userId.toString());
                    if (!updateImg) {
                        System.out.println("头像修改失败");
                        return "1";
                    }else {
                        System.out.println("头像修改成功");
                        request.getSession().setAttribute(sessionKeyUserHeadImg,imagePath);
                        return "3";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String imagePath = (String) request.getSession().getAttribute(sessionKeyUserHeadImg);
                userService.updateUserImgById(imagePath, userId.toString());
            }
        } else {
            System.out.println("连接超时");
            return "2";
        }
        return null;
    }

    //修改昵称
    @RequestMapping("/updateNameById")
    @ResponseBody
    public String updateNameById(String name,HttpServletRequest request){
        String userPhone = (String) request.getSession().getAttribute(sessionKeyUserPhone);
        if (userPhone != null) {
            Long userId = userService.selectUserIdByPhone(userPhone);
            boolean b = userService.updateNameById(name, userId);
            if (!b){
                System.out.println("修改昵称失败");
                return "1";
            }else {
                System.out.println("修改昵称成功");
                request.getSession().setAttribute(sessionKeyUserName,name);
                return "3";
            }
        }else {
            System.out.println("连接超时");
            return "2";
        }
    }
}
