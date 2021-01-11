package com.spring.mvc.portfolio.repository;

import com.spring.mvc.portfolio.entities.TStock;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "tStockRepository")
public interface TStockRepository extends JpaRepository<TStock, Integer>{
    @Transactional
    @Modifying
    @Query(value = "UPDATE TStock SET name=?2, symbol=?3, classify_id=?4 WHERE id=?1", nativeQuery = true)
    public void update(@Param("id") Integer id, @Param("name") String name, @Param("symbol") String symbol, @Param("classify_id") Integer classify_id);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE TStock SET changePrice=?2, changeInPercent=?3, preClosed=?4, price=?5, transactionDate=?6, volumn=?7 WHERE id=?1", nativeQuery = true)
    public void updatePrice(@Param("id") Integer id, @Param("changePrice") BigDecimal changePrice, @Param("changeInPercent") BigDecimal changeInPercent, @Param("preClosed") BigDecimal preClosed, @Param("price") BigDecimal price, @Param("transactionDate") Date transactionDate, @Param("volumn") Long volumn);

}