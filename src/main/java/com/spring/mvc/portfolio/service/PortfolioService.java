package com.spring.mvc.portfolio.service;

import com.spring.mvc.portfolio.repository.ClassifyRepository;
import com.spring.mvc.portfolio.repository.InvestorRepository;
import com.spring.mvc.portfolio.repository.PortfolioRepository;
import com.spring.mvc.portfolio.repository.TStockRepository;
import com.spring.mvc.portfolio.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {
    @Autowired
    private ClassifyRepository classifyRepository;
    
    @Autowired
    private TStockRepository tStockRepository;
    
    @Autowired
    private InvestorRepository investorRepository;
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @Autowired
    private WatchRepository watchRepository;

    public ClassifyRepository getClassifyRepository() {
        return classifyRepository;
    }

    public TStockRepository gettStockRepository() {
        return tStockRepository;
    }

    public InvestorRepository getInvestorRepository() {
        return investorRepository;
    }

    public PortfolioRepository getPortfolioRepository() {
        return portfolioRepository;
    }

    public WatchRepository getWatchRepository() {
        return watchRepository;
    }
    
    
}
