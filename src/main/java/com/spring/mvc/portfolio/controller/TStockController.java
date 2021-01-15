package com.spring.mvc.portfolio.controller;

import com.spring.mvc.portfolio.entities.Classify;
import com.spring.mvc.portfolio.entities.TStock;
import com.spring.mvc.portfolio.service.PortfolioService;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio/tstock")
public class TStockController {

    @Autowired
    private PortfolioService service;

    @PostMapping(value = {"/", "/add"})
    @Transactional
    public TStock add(@RequestBody Map<String, String> map) {
        Classify classify = service.getClassifyRepository().findOne(Integer.parseInt(map.get("classify_id")));
        TStock ts = new TStock();
        ts.setName(map.get("name"));
        ts.setSymbol(map.get("symbol"));
        ts.setClassify(classify);
        service.gettStockRepository().save(ts);
        return ts;
    }
    
    @PutMapping(value = {"/", "/update"})
    @Transactional
    public TStock update(@RequestBody Map<String, String> map) {
        Classify classify = service.getClassifyRepository().findOne(Integer.parseInt(map.get("classify_id")));
        TStock ts = service.gettStockRepository().findOne(Integer.parseInt(map.get("id")));
        ts.setName(map.get("name"));
        ts.setSymbol(map.get("symbol"));
        ts.setClassify(classify);
        //service.gettStockRepository().update(Long.parseLong(map.get("id")), map.get("name"), map.get("symbol"));
        return ts;
    }
    
    @DeleteMapping(value = {"/{id}", "/delete/{id}"})
    @Transactional
    public Boolean delete(@PathVariable("id") Integer id) {
        service.gettStockRepository().delete(id);
        return true;
    }
    
    @GetMapping(value = {"/{id}", "/get/{id}"})
    @Transactional
    public TStock get(@PathVariable("id") Integer id) {
        TStock tStock = service.gettStockRepository().findOne(id);
        return tStock;
    }

    @GetMapping(value = {"/", "/query"})
    public Iterable<TStock> query() {
        return service.gettStockRepository().findAll();
    }

}