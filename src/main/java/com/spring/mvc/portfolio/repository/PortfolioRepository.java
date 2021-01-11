package com.spring.mvc.portfolio.repository;

import com.spring.mvc.portfolio.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "portfolioRepository")
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer>{

}