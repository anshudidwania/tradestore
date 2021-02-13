package com.trade.tradestore.service;

import com.trade.tradestore.entity.TradeStore;
import com.trade.tradestore.exception.TradeStoreException;
import com.trade.tradestore.modal.TradeStoreDto;
import com.trade.tradestore.repository.TradeStoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TradeStoreServiceTest {
    @Mock
    private TradeStoreRepository tradeStoreRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private TradeStoreService tradeStoreService;

    @Test
    public void shouldUpdateTradeStoreSuccessfullyIfVersionIdIsMore() {
        TradeStore existingTradeStore = TradeStore.builder()
                .tradeId("T1")
                .bookId("b1")
                .counterPartyId("cp1")
                .maturityDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .expired('N')
                .version(1)
                .build();

        TradeStoreDto tradeStoreDto = TradeStoreDto.builder()
                .tradeId("T1")
                .bookId("b1")
                .counterPartyId("cp1")
                .maturityDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .expired('N')
                .version(2)
                .build();
        when(tradeStoreRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(existingTradeStore));
        when(modelMapper.map(any(), any())).thenReturn(existingTradeStore);
        when(tradeStoreRepository.save(any(TradeStore.class))).thenReturn(existingTradeStore);
        Boolean bs = tradeStoreService.updateTradeStore(tradeStoreDto);
    }

    @Test
    public void shouldUpdateSuccessfullyForNewTrade() {
        TradeStore existingTradeStore = null;

        TradeStoreDto tradeStoreDto = TradeStoreDto.builder()
                .tradeId("T1")
                .bookId("b1")
                .counterPartyId("cp1")
                .maturityDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .expired('N')
                .version(2)
                .build();
        when(tradeStoreRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(existingTradeStore));
        when(modelMapper.map(any(), any())).thenReturn(null);
        Boolean bs = tradeStoreService.updateTradeStore(tradeStoreDto);
    }

    @Test
    public void shouldUpdateTradeStoreSuccessfullyIfVersionIdIsSame() {
        TradeStore existingTradeStore = TradeStore.builder()
                .tradeId("T1")
                .bookId("b1")
                .counterPartyId("cp1")
                .maturityDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .expired('N')
                .version(1)
                .build();

        TradeStoreDto tradeStoreDto = TradeStoreDto.builder()
                .tradeId("T1")
                .bookId("b1")
                .counterPartyId("cp1")
                .maturityDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .expired('N')
                .version(1)
                .build();
        when(tradeStoreRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(existingTradeStore));
        when(modelMapper.map(any(), any())).thenReturn(existingTradeStore);
        when(tradeStoreRepository.save(any(TradeStore.class))).thenReturn(existingTradeStore);
        Boolean bs = tradeStoreService.updateTradeStore(tradeStoreDto);
    }

    @Test(expected = TradeStoreException.class)
    public void shouldThrowExceptionIfVersionIdIsLess() {
        TradeStore existingTradeStore = TradeStore.builder()
                .tradeId("T1")
                .bookId("b1")
                .counterPartyId("cp1")
                .maturityDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .expired('N')
                .version(1)
                .build();

        TradeStoreDto tradeStoreDto = TradeStoreDto.builder()
                .tradeId("T1")
                .bookId("b1")
                .counterPartyId("cp1")
                .maturityDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .expired('N')
                .version(0)
                .build();
        when(tradeStoreRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(existingTradeStore));
        Boolean bs = tradeStoreService.updateTradeStore(tradeStoreDto);
    }


    @Test(expected = TradeStoreException.class)
    public void shouldThrowExceptionIfMaturityIsLessThanCurrentDate() {
        TradeStore existingTradeStore = null;

        TradeStoreDto tradeStoreDto = TradeStoreDto.builder()
                .tradeId("T1")
                .bookId("b1")
                .counterPartyId("cp1")
                .maturityDate(LocalDate.now().minusDays(1))
                .createdDate(LocalDate.now())
                .expired('N')
                .version(0)
                .build();
        when(tradeStoreRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(existingTradeStore));
        Boolean bs = tradeStoreService.updateTradeStore(tradeStoreDto);
    }

}