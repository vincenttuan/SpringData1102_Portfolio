package com.spring.mvc.portfolio.controller;

import com.spring.mvc.portfolio.entities.TStock;
import com.spring.mvc.portfolio.service.PortfolioService;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@RestController
@RequestMapping("/portfolio/price")
public class PriceController {

    // 個股報價資訊(Watch List用)
    // 歷史報價資訊(走勢圖用)
    // 範例 : /histquotes/^TWII 
    // 範例 : /histquotes/2330.TW 
    @GetMapping(value = {"/histquotes/{symbol:.+}"})
    public List<HistoricalQuote> queryHistoricalQuotes(@PathVariable("symbol") Optional<String> symbol) {
        List<HistoricalQuote> historicalQuotes = null;
        try {
            Calendar from = Calendar.getInstance();
            Calendar to = Calendar.getInstance();
            from.add(Calendar.YEAR, -1); // from 1 year ago
            Stock stock = YahooFinance.get(symbol.get());
            historicalQuotes = stock.getHistory(from, to, Interval.DAILY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return historicalQuotes;
    }
}
