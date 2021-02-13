package com.trade.tradestore.web;

import com.trade.tradestore.modal.TradeStoreDto;
import com.trade.tradestore.service.TradeStoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TradeStoreControllerTest {
    @Mock
    private TradeStoreService tradeStoreService;
    @InjectMocks
    private TradeStoreController tradeStoreController;


    @Test
    public void shouldStoreTradeSuccessfully(){
        when(tradeStoreService.updateTradeStore(any(TradeStoreDto.class))).thenReturn(true);
        TradeStoreDto tradeStoreDto = TradeStoreDto.builder()
                .tradeId("T1")
                .bookId("b1")
                .counterPartyId("cp1")
                .maturityDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .expired('N')
                .build();
         ResponseEntity<String> response = tradeStoreController.updateTradeStore(tradeStoreDto);
         assertThat(response.getBody()).isEqualTo("SUCCESS");

    }

}