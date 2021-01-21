package com.dzc.llt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author:dzc
 * @date 2021-01-04 9:49
 */

@Controller
public class IndexController {

    /**
     * 进入首页（未登录）
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "首页";
    }

    /**
     * 进入登录界面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "登录";
    }

    /**
     * 进入注册界面
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "注册";
    }

    /**
     * 进入登录后界面
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/after_login")
    public String after_login(HttpSession session, Model model) {
        String name = (String) session.getAttribute("loginUser");
        model.addAttribute("name", name);
        return "首页-登录后";
    }

    /**
     * @author:lzr
     * @date 2021-01-13 22:49
     * @param name
     * @return
     */
    @RequestMapping("/my_info")
    public String my_info(String name){
        return "我的业务";
    }

}
