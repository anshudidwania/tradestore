package com.trade.tradestore.modal;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TradeStoreDto {
    private String tradeId;
    private Integer version;
    private String counterPartyId;
    private String bookId;
    private String maturityDate;
    private Character expired;


}
