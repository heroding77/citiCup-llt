package com.dzc.llt.Mail;

/**
 * @author:dzc
 * @date 2021-01-04 9:57
 */


public interface MailService {
    void sendHTMLMail(String to, String subject, String content);
}
