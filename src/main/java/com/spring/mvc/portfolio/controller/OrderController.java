package com.spring.mvc.portfolio.controller;


import com.spring.mvc.portfolio.entities.Investor;
import com.spring.mvc.portfolio.entities.Portfolio;
import com.spring.mvc.portfolio.entities.TStock;
import com.spring.mvc.portfolio.repository.InvestorRepository;
import com.spring.mvc.portfolio.repository.PortfolioRepository;
import com.spring.mvc.portfolio.repository.TStockRepository;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio/order")
public class OrderController {
    
    @Autowired
    private InvestorRepository investorRepository;
    
    @Autowired
    private TStockRepository tStockRepository;
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    // 買進
    @GetMapping(value = {"/buy/{tstock_id}/{amount}"})
    @Transactional
    public String buy(HttpSession session, @PathVariable("tstock_id") Integer tstock_id, @PathVariable("amount") Integer amount) {
        Investor login_investor = (Investor)session.getAttribute("investor");
        Investor investor = investorRepository.findOne(login_investor.getId());
        if(investor == null) return "Investor None";
        TStock ts = tStockRepository.findOne(tstock_id);
        if(ts == null) return "TStock None";
        
        int buyTotalCost = (int)(ts.getPrice().doubleValue() * amount);
        
        int balance = investor.getBalance() - buyTotalCost;
        if(balance < 0) {
            return "Insufficient balance";
        }
        investor.setBalance(balance);
        
        Portfolio po = new Portfolio();
        po.setInvestor(investor);
        po.settStock(ts);
        po.setCost(ts.getPrice().doubleValue());
        po.setAmount(amount);
        po.setDate(new Date());
        
        investorRepository.saveAndFlush(investor);
        portfolioRepository.saveAndFlush(po);
        
        return po.getId() + "";
    }
    
    // 賣出
    @GetMapping(value = {"/sell/{id}/{amount}"})
    @Transactional
    public String sell(HttpSession session, @PathVariable("id") Integer id, @PathVariable("amount") Integer amount) {
        Investor login_investor = (Investor)session.getAttribute("investor");
        Investor investor = investorRepository.findOne(login_investor.getId());
        if(investor == null) return "Investor None";
        
        Portfolio po = portfolioRepository.findOne(id);
        if(po == null) return "Portfolio None";
        
        po.setAmount(po.getAmount() - amount);
        
        // 回滾利潤
        int profit = (int)(amount * po.gettStock().getPrice().doubleValue());
        investor.setBalance(investor.getBalance() + profit);
        
        portfolioRepository.saveAndFlush(po);
        investorRepository.saveAndFlush(investor);
        
        return "OK";
    }

    
}
