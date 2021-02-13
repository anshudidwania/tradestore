package com.trade.tradestore.exception;

public class TradeStoreException extends RuntimeException {

    private static final long serialVersionUID = 3663950444600663161L;

    public TradeStoreException(String message) {
        super(message);
    }

    public TradeStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
