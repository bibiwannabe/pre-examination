package com.libiyi.exa.server.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class EmailUtil {
    static Logger logger = LogManager.getLogger(EmailUtil.class);

    public static void sendCode(Integer code, String email) {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        senderImpl.setHost("smtp.sina.com");
        senderImpl.setUsername("bibiwannabe@sina.com");
        senderImpl.setPassword("LIBIYIlibiyi272");
        try {
            MimeMessage mailMessage = senderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(
                    mailMessage, false, "utf-8");
            messageHelper.setTo(email);
            messageHelper.setFrom("bibiwannabe@sina.com");
            messageHelper.setSubject("【考前辅导系统】注册验证");
            messageHelper.setText("您的验证码为："+code+"，该验证码5分钟内有效。", true);
            senderImpl.send(mailMessage);
        } catch (Exception e) {
            logger.error("发送邮件失败，email:{}", email, e);
        }

    }
}
