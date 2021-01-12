package com.dzc.llt.Controller;

import com.dzc.llt.Mail.MailService;
import com.dzc.llt.Mail.VerCodeGenerateUtil;
import com.dzc.llt.Pojo.Company;
import com.dzc.llt.Pojo.User;
import com.dzc.llt.Service.CompanyService;
import com.dzc.llt.Service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:dzc
 * @date 2021-01-04 9:41
 */

@Controller
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("companyService")
    private CompanyService companyService;

    @Autowired
    @Qualifier("mailService")
    private MailService mailService;

    /**
     * 接受 提交注册数据
     * @param username
     * @param email
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/register_data")
    public String register_data(@RequestParam String username, String email, String password, Model model){
        System.out.println("收到请求");
        String message1 = null;
        if(userService.existsUser(username)){    //若存在此用户名  不允许重复注册
            message1 = "用户已存在，请重新输入！";
            model.addAttribute("message1", message1);
            return "注册";
        }else{          //不存在此用户名，允许注册
            User user =new User(username,password,email);
            userService.saveUser(user);
            return "注册成功";
        }
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/login_data")
    public String login_data(@RequestParam String username, String password, Model model, HttpSession session) {
        String message2 = null;
        String message3 = null;
        if (username.equals("")) {
            message2 = "请输入用户名！";
            model.addAttribute("message2", message2);
            return "登录";
        }
        if (companyService.existsCompany(username)) {
            Company s = companyService.findCompany(username);
            if (password.equals(s.getPassword())) {
                session.setAttribute("loginUser", username);
                return "redirect:/WH_business";
            } else {
                message3 = "密码有误！";
                model.addAttribute("message3", message3);
                return "登录";
            }
        }
        if (!userService.existsUser(username)) {     //不存在此用户
            //提示前端不存在
            message2 = "该用户不存在！";
            model.addAttribute("message2", message2);
            return "登录";
        } else {
            User userBean = userService.findUer(username);
            //用户存在且密码相同
            if (userBean.getPassword().equals(password)) {
                //提示
                System.out.println("密码相同");
                model.addAttribute("name", username);
                session.setAttribute("loginUser", username);
                System.out.println(session);
                return "首页-登录后";
            } else {   //如果密码与数据库相同   则提示前端用户名/密码错误
                System.out.println(userBean.getPassword() + "\n" + password);
                message3 = "密码有误！";
                model.addAttribute("message3", message3);
                return "登录";
            }
        }
    }

    /**
     * 安卓端登录请求路径
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping("/android/login_data")
    public JSONObject login_android_data(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, Integer> map = new HashMap<>();
        if (!userService.existsUser(username)) {     //不存在此用户
            //提示前端不存在
            map.put("isLogin",1);
        } else {
            User userBean = userService.findUer(username);
            //用户存在且密码相同
            if (userBean.getPassword().equals(password)) {
                map.put("isLogin",0);
            } else {   //如果密码与数据库不相同   则提示前端用户名/密码错误
                map.put("isLogin",2);
            }
        }
        return JSONObject.fromObject(map);
    }

    /**
     * 安卓端注册请求路径
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping("/android/register_data")
    public JSONObject register_android_data(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Map<String, Integer> map = new HashMap<>();
        if(userService.existsUser(username)){
            //若存在此用户名，不允许重复注册
            map.put("isRegister", 1);
        }else{
            //不存在此用户名，允许注册
            User user =new User(username,password,email);
            userService.saveUser(user);
            map.put("isRegister", 0);
        }
        return JSONObject.fromObject(map);
    }

    /**
     * 安卓端接收验证码
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping("/android/verification_code")
    public JSONObject verification_android_code(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        Map<String, String> map = get_verification_code(email);
        return JSONObject.fromObject(map);
    }

    /**
     * 发送验证码
     * @param email_add
     * @return
     */
    public Map<String, String> get_verification_code(String email_add) {    //返回类型为Map
        System.out.println("。。。");
        Map<String, String> resultMap = new HashMap<>();
        String code = VerCodeGenerateUtil.generateVerCode();
        mailService.sendHTMLMail(email_add, "Hello!", "尊敬的用户,您好:\n"
                + "\n本次请求的邮件验证码为:" + code + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");
        resultMap.put("result","验证码已发送至"+email_add);
        resultMap.put("verificationCode",code);    //key是验证码的键
        return resultMap;
    }
}
