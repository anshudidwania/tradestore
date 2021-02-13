package com.trade.tradestore.service;

import com.trade.tradestore.repository.TradeStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class ExpiryFlagScheduler {

    @Autowired
    private TradeStoreRepository tradeStoreRepository;

    @Scheduled(cron = "${expiry.flag.cron}")
    public void updateFlagScheduler(){
        tradeStoreRepository.updateExpiryFlag();
    }

}
