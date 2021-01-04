package com.dzc.llt.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author:dzc
 * @date 2021-01-04 9:57
 */

@Service("mailService")
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("heroding77@163.com")
    private String from;
    /**
     * 发送html格式的邮件
     * @param to 接受者
     * @param subject 主题
     * @param content 内容
     */

    @Override
    public void sendHTMLMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setText(content);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailSender.send(mailMessage);
    }
}
