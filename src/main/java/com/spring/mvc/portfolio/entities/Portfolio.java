package com.spring.mvc.portfolio.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Portfolio implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    
    @Column
    private Double cost;
    
    @Column
    private Integer amount;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @ManyToOne()
    @JoinColumn(name = "investor_id", referencedColumnName = "id")
    @JsonIgnoreProperties("portfolios")
    private Investor investor;
    
    @OneToOne()
    @JoinColumn(name = "tStock_id", 
                foreignKey = @ForeignKey(name="tStock_fk", 
                                         value = ConstraintMode.CONSTRAINT))
    private TStock tStock;
    
    public Portfolio() {
    }

    public Portfolio(Double cost, Integer amount, Investor investor, TStock tStock) {
        this.cost = cost;
        this.amount = amount;
        this.investor = investor;
        this.tStock = tStock;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public TStock gettStock() {
        return tStock;
    }

    public void settStock(TStock tStock) {
        this.tStock = tStock;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
