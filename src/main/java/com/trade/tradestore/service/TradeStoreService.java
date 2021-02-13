package com.trade.tradestore.service;

import com.trade.tradestore.entity.TradeStore;
import com.trade.tradestore.exception.TradeStoreException;
import com.trade.tradestore.modal.TradeStoreDto;
import com.trade.tradestore.repository.TradeStoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TradeStoreService {
    @Autowired
    private TradeStoreRepository tradeStoreRepository;
    @Autowired
    private ModelMapper modalMapper;

    public Boolean updateTradeStore(TradeStoreDto tradeStore) {
        Optional<TradeStore> existingTs = tradeStoreRepository.findById(tradeStore.getTradeId());
        validateTrades(tradeStore, existingTs);
        tradeStore.setExpired('N');
        tradeStoreRepository.save(modalMapper.map(tradeStore, TradeStore.class));
        return true;
    }

    private void validateTrades(TradeStoreDto tradeStore, Optional<TradeStore> previousTradeStore) {
        if (previousTradeStore.isPresent() && (tradeStore.getVersion() < previousTradeStore.get().getVersion())) {
            throw new TradeStoreException("Version id is less than previous version id");
        }
        if (tradeStore.getMaturityDate().compareTo(LocalDate.now()) < 0) {
            throw new TradeStoreException("Maturity date cannot be less than current date");
        }
    }


}
