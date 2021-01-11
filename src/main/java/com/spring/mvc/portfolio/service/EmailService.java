package com.spring.mvc.portfolio.service;

import com.spring.mvc.portfolio.entities.Investor;
import com.spring.mvc.portfolio.mail.SendEmail;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private ServletContext servletContext;
    
    @Async
    public void send(final Investor investor) {
        Runnable r = () -> {
            String personal = "我的投資組合"; // 發送者姓名
            String to = investor.getEmail(); // 發送給誰 ? 若有多筆", "號隔開
            String title = "我的投資組合會員Email驗證通知"; // 信件 title
            String url = "http://localhost:8080" + servletContext.getContextPath() + "/mvc/portfolio/verify/%d/%s";
            url = String.format(url, investor.getId(), investor.getCode());
            String html = "Dear 顧客您好," // 信件內容 
                    + "<p /><a href='" + url + "'>Email驗證網址</a>"
                    + "<p /> Please do not spam my email!";
            SendEmail sendEmail = new SendEmail();
            try {
                sendEmail.submit(personal, to, title, html);
            } catch (Exception e) {
                System.out.println("Email send error: " + e);
                e.printStackTrace();
            }
            
        };
        new Thread(r).start();
    }
}
