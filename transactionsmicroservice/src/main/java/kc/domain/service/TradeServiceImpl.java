package kc.domain.service;/*
package krystianciamaga.com.demo.service;


import krystianciamaga.com.demo.entity.Trade;

import krystianciamaga.com.demo.repository.TradeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TradeServiceImpl implements TradeService {


    private final TradeRepository tradeRepository;

    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public List<Trade> findAllTrades(int pageNumber,int pageSize) {
        return tradeRepository.findAll(PageRequest.of(pageNumber,pageSize)).stream()
                .collect(Collectors.toList());
    }

    @Override
    public Trade findTradeById(String id) {

        Optional<Trade> trade = Optional.ofNullable(tradeRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException()));

        return trade.get();
    }

    @Override
    public void deleteTradeById(String id) {

        if(tradeRepository.existsById(id)){
            tradeRepository.deleteById(id);
        }else{
            throw new RuntimeException();
        }

    }

    @Override
    public void addTrade(Trade trade) {

<<<<<<< HEAD
*/
/*
=======
>>>>>>> parent of fb3f18a... .
        if(tradeRepository.existsById(trade.getId())){
            throw new RuntimeException();
        }else {

            tradeRepository.save(trade);
<<<<<<< HEAD
*//*

=======
>>>>>>> parent of fb3f18a... .

        }

    }


    @Override
    public void updateTradeById(Trade tradeDto, String id) {

        Optional<Trade> trade = Optional.ofNullable(tradeRepository.findById(id)
                .orElseThrow(RuntimeException::new));

      */
/*  Trade newTrade = TradeMapper.mapTradeDtoToTrade(tradeDto);

        newTrade.setId(trade.get().getId());
        tradeRepository.save(newTrade);*//*



    }
}
*/
