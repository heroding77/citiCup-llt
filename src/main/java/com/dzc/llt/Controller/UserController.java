package com.dzc.llt.Controller;

import com.dzc.llt.Pojo.Company;
import com.dzc.llt.Pojo.User;
import com.dzc.llt.Service.CompanyService;
import com.dzc.llt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
}
