package com.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestEngine {

	public static void main(String[] args) {
		List<CommonStock> commonStocks = getCommonStockList();
		displayCommonStocksAndTheirLiquidityHealth(commonStocks);
		displayTopThreeCommonStocksByTheirPreviousClosingPrice(commonStocks);

	}

	private static List<CommonStock> getCommonStockList() {
		return Arrays.asList(new CommonStock("goog", "google", new BigDecimal(90.26), new BigDecimal(90.26), 2.46),
				new CommonStock("tsla", "tesla", new BigDecimal(150.23), new BigDecimal(150.23), 1.46),
				new CommonStock("f", "ford", new BigDecimal(12.12), new BigDecimal(12.12), 1.2),
				new CommonStock("aapl", "apple", new BigDecimal(134.51), new BigDecimal(134.51), 0.86));
	}
	
	private static void displayCommonStocksAndTheirLiquidityHealth(List<CommonStock> commonStocks) 
	{
		// Using Collectors.toMap, print stocks and their liquidity health
		Map<String, String> commonStocksAndTheirLiquidityHealth = commonStocks.stream()
				.collect(Collectors.toMap(CommonStock::getSymbol, cs -> {
					if (cs.getWorkingCapitalRatio() >= 2) {
						return "Healthy Liquidity With A Working Capital Ratio of " + cs.getWorkingCapitalRatio();
					} else {
						return "Unhealthy Liquidity With A Working Capital Ratio of " + cs.getWorkingCapitalRatio();
					}
				}, (cs1, cs2) -> {
					return cs1;
				}));
		commonStocksAndTheirLiquidityHealth.forEach((name, liquidity) -> System.out.println(name + " : " + liquidity));
	}

	private static void displayTopThreeCommonStocksByTheirPreviousClosingPrice(List<CommonStock> commonStocks) 
	{
		int numberOfStocks = commonStocks.size();
		List<CommonStock> commonStocksSorted = commonStocks.stream()
				.sorted(Comparator.comparing(CommonStock::getPreviousClosingPrice)).skip(numberOfStocks - 3)
				.collect(Collectors.toList());
		commonStocksSorted.forEach(c -> System.out.println(c.getName() + " : Previous Closing Price  $" + c.getPreviousClosingPrice().doubleValue()));
	}
}
