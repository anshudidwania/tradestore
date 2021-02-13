package com.trade.tradestore.web;

import com.trade.tradestore.modal.TradeStoreDto;
import com.trade.tradestore.service.TradeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TradeStoreController {

    @Autowired
    private TradeStoreService tradeStoreService;

    @PostMapping(value = "/trade-store")
    public ResponseEntity<String> updateTradeStore(@RequestBody TradeStoreDto tradeStore) {
        tradeStoreService.updateTradeStore(tradeStore);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

}

