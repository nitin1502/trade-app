package com.jpmc.trade.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jpmc.trade.enums.BuySellIndicator;
import com.jpmc.trade.model.Trade;
import com.jpmc.trade.util.TradeUtil;

public class TradeDaoImpl implements TradeDao {

	private List<Trade> trades = new ArrayList<Trade>();

	public BigDecimal fetchAmountInUSDSettled(BuySellIndicator buySellIndicator) {
		List<Trade> tradesSettled = trades.stream().filter(trade -> trade.getIndicator() == buySellIndicator)
				.collect(Collectors.toList());
		BigDecimal total = BigDecimal.ZERO;
		for (Trade trade : tradesSettled) {
			total = total.add(trade.getUSDAmountOfTrade());
		}
		return total;
	}

	public BigDecimal fetchAmountInUSDSettled(LocalDate settlementDate, BuySellIndicator buySellIndicator) {
		List<Trade> tradesSettled = trades.stream()
				.filter(trade -> trade.getIndicator() == buySellIndicator
						&& TradeUtil.getBusinessDateByCurrency(trade.getCurrency(), trade.getSettlementDate())
								.equals(settlementDate))
				.collect(Collectors.toList());
		BigDecimal total = BigDecimal.ZERO;
		for (Trade trade : tradesSettled) {
			total = total.add(trade.getUSDAmountOfTrade());
		}
		return total;
	}

	public List<Trade> fetchEntitiesBasedOnRanking(BuySellIndicator buySellIndicator) {
		return trades.stream().filter(trade -> trade.getIndicator() == buySellIndicator)
				.sorted((trade1, trade2) -> trade2.getUSDAmountOfTrade().compareTo(trade1.getUSDAmountOfTrade()))
				.collect(Collectors.toList());
	}

	public void createTrade(Trade trade) {
		// TODO: persist to DB
		if (null != trade) {
			trades.add(trade);
		}
	}

}
