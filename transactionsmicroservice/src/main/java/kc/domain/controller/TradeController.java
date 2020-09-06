package kc.domain.controller;/*
package krystianciamaga.com.demo.controller;



import krystianciamaga.com.demo.entity.Trade;
import krystianciamaga.com.demo.service.TradeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trades")
public class TradeController {



    private final TradeServiceImpl tradeService;

    public TradeController(TradeServiceImpl tradeService) {
        this.tradeService = tradeService;
    }


    @GetMapping("{pageNumber}/{pageSize}")
    public List<Trade> getAllTrades(@PathVariable("pageNumber") final Integer pageNumber, @PathVariable("pageSize") final Integer pageSize){
        return tradeService.findAllTrades(pageNumber,pageSize);
    }

    @GetMapping("/{id}")
    public Trade getTradeById(@PathVariable String id){
        return tradeService.findTradeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTradeById(@PathVariable String id){
        tradeService.deleteTradeById(id);

    }

    @PutMapping("/{id}")
    public void updateTradeById(@RequestBody Trade tradeDto, @PathVariable String id){
        tradeService.updateTradeById(tradeDto,id);
    }

    @PostMapping
    public void addTrade(@RequestBody Trade tradeDto){
        tradeService.addTrade(tradeDto);
    }


}
*/
