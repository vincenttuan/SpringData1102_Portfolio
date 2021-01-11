package com.spring.mvc.portfolio.mail;

/** *********************************************************
1. 在 pom.xml 中加入 :
<dependency>
 <groupId>com.sun.mail</groupId>
 <artifactId>javax.mail</artifactId>
 <version>1.6.2</version>
</dependency>

2. Google 二階段驗證啟用 https://www.google.com/landing/2step/?utm_source=pp&hl=zh_TW
3. 申請應用程式密碼 https://security.google.com/settings/security/apppasswords
**********************************************************
 */

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

public class SendEmail {
    public static void main(String[] args) throws Exception {
        String personal = ""; // 發送者姓名
        String to = ""; // 發送給誰 ? 若有多筆", "號隔開
        String title = ""; // 信件 title
        // 信件內容 
        String html = "";
        
        SendEmail sendEmail = new SendEmail();
        sendEmail.submit(personal, to, title, html);
    }
    
    public void submit(String personal, String to, String title, String html) throws Exception {
        // Gmail 與 授權碼(非 Google 密碼)
        final String googleGmail = "xxx@gmail.com"; // 你的 Gmail
        final String authPassword = "xxxxxxxxxxxxxx"; // 你的授權碼

        // smpt 設定資訊
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        // 會話建立與 smtp 溝通
        Session session;
        session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(googleGmail, authPassword);
            }
        });
        
        // 建立 Email 實體
        Message message = new MimeMessage(session);
        
        // 發文者 一定會是你的 google email
        InternetAddress ia = new InternetAddress("xxx@gmail.com"); // 你的 Gmail
        ia.setPersonal(personal);
        message.setFrom(ia);
        
        // 受文者
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );
        
        // Email 抬頭
        message.setSubject(title);

        // Email 內容純文字
        //message.setText("Dear Mail Crawler,\n\n Please do not spam my email!");
        
        // Email 內容 HTML
        message.setContent(html, "text/html;charset=utf-8");

        // 發送 email
        Transport.send(message);

        System.out.println("信件發送成功 !");

    }

}
