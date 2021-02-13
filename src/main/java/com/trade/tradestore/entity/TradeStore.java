package com.trade.tradestore.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TRADE_STORE")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TradeStore {
    @Id
    @Column(name = "TRADE_ID")
    private String tradeId;

    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "COUNTER_PARTY_ID")
    private String counterPartyId;

    @Column(name = "MATURITY_DATE")
    private LocalDate maturityDate;

    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;

    @Column(name = "EXPIRED")
    private Character expired;

    @Column(name = "BOOK_ID")
    private String bookId;
}
