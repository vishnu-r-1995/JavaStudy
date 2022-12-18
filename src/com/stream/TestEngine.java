package com.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TestEngine {

	public static void main(String[] args) {
		List<CommonStock> commonStocks = getCommonStockList();

	}

	private static List<CommonStock> getCommonStockList() {
		return Arrays.asList(new CommonStock("goog", "google", new BigDecimal(90.26), new BigDecimal(90.26), 2.46),
				new CommonStock("tsla", "tesla", new BigDecimal(150.23), new BigDecimal(150.23), 1.46),
				new CommonStock("f", "ford", new BigDecimal(12.12), new BigDecimal(12.12), 1.2),
				new CommonStock("aapl", "apple", new BigDecimal(134.51), new BigDecimal(134.51), 0.86));
	}

}
