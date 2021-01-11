package com.spring.mvc.portfolio.controller;


import com.spring.mvc.portfolio.entities.Investor;
import com.spring.mvc.portfolio.service.PortfolioService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/portfolio")
public class LoginController {
    
    @Autowired
    private PortfolioService service;
    
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) {
        Investor investor = service.getInvestorRepository().getInvestor(username);
        if(investor != null && investor.getPassword().equals(password)) {
            session.setAttribute("investor", investor);
            session.setAttribute("watch_id", investor.getWatchs().iterator().next().getId());
            return "redirect:/portfolio/index.jsp";
        }
        session.invalidate();
        return "redirect:/portfolio/login.jsp";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/portfolio/login.jsp";
    }
}
