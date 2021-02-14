package com.trade.tradestore.service;

import com.trade.tradestore.entity.TradeStore;
import com.trade.tradestore.exception.TradeStoreException;
import com.trade.tradestore.modal.TradeStoreDto;
import com.trade.tradestore.repository.TradeStoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class TradeStoreService {
    @Autowired
    private TradeStoreRepository tradeStoreRepository;
    @Autowired
    private ModelMapper modalMapper;

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public Boolean updateTradeStore(TradeStoreDto tradeStore) {
        Optional<TradeStore> existingTs = tradeStoreRepository.findById(tradeStore.getTradeId());
        validateTrades(tradeStore, existingTs);
        tradeStore.setExpired('N');
        TradeStore store = modalMapper.map(tradeStore, TradeStore.class);
        store.setCreatedDate(LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        //store.setMaturityDate(convertDate(DATE_FORMAT, tradeStore.getMaturityDate()));
        tradeStoreRepository.save(store);
        return true;
    }

    private void validateTrades(TradeStoreDto tradeStore, Optional<TradeStore> previousTradeStore) {
        if (previousTradeStore.isPresent() && (tradeStore.getVersion() < previousTradeStore.get().getVersion())) {
            throw new TradeStoreException("Version id is less than previous version id");
        }
        if (convertDate(DATE_FORMAT, tradeStore.getMaturityDate()).compareTo(LocalDate.now()) < 0) {
            throw new TradeStoreException("Maturity date cannot be less than current date");
        }
    }
    private LocalDate convertDate(String format, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, formatter);
    }



}
