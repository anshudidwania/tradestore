package com.trade.tradestore.repository;

import com.trade.tradestore.entity.TradeStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TradeStoreRepository extends JpaRepository<TradeStore, String> {
    @Modifying
    @Query(value = "Update TradeStore ts set ts.expired = 'Y' where ts.maturityDate< sysdate")
    public void updateExpiryFlag() ;

}
