package com.spring.mvc.portfolio.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT p.investor_id as id, c.name as name, SUM(p.amount * s.price) as subtotal " +
           "FROM Classify c, Portfolio p, TStock s " +
           "WHERE p.tStock_id = s.id AND s.classify_id = c.id " +
           "GROUP BY p.investor_id, c.name") // p.investor.id=:id AND 
public class Asset {
    @Id
    private Integer id;
    
    @Column
    private String name;
    
    @Column
    private Double subtotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "Asset{" + "id=" + id + ", name=" + name + ", subtotal=" + subtotal + '}';
    }

    
    
}
