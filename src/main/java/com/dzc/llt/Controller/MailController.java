package com.dzc.llt.Controller;


import com.dzc.llt.Mail.MailService;
import com.dzc.llt.Mail.VerCodeGenerateUtil;
import com.dzc.llt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MailController {

    @Autowired
    @Qualifier("mailService")
    private MailService mailService;

    @RequestMapping("/verification_code")
    @ResponseBody
    public Map<String, Object> get_mailService(String email_add) {    //返回类型为Map
        System.out.println("。。。");
        Map<String, Object> resultMap = new HashMap<>();
        String code = VerCodeGenerateUtil.generateVerCode();
        mailService.sendHTMLMail(email_add, "Hello!", "尊敬的用户,您好:\n"
                + "\n本次请求的邮件验证码为:" + code + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");
        resultMap.put("result","验证码已发送至"+email_add);
        resultMap.put("key",code);    //key是验证码的键
        return resultMap;
    }


}
