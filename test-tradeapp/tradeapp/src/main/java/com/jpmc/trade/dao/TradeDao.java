package com.jpmc.trade.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.jpmc.trade.enums.BuySellIndicator;
import com.jpmc.trade.model.Trade;

public interface TradeDao {
    BigDecimal fetchAmountInUSDSettled(BuySellIndicator buySellIndicator);
    
    BigDecimal fetchAmountInUSDSettled(LocalDate settlementDate, BuySellIndicator  buySellIndicator);

    List<Trade> fetchEntitiesBasedOnRanking(BuySellIndicator buySellIndicator);

    void createTrade(Trade trade);

}
