package com.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;

import com.google.common.base.Preconditions;

public class TestEngine {

	public static void main(String[] args) {
		List<CommonStock> commonStocks = getCommonStockList();
		displayCommonStocksAndTheirLiquidityHealth(commonStocks);
		displayTopThreeCommonStocksByTheirPreviousClosingPrice(commonStocks);
		List<Book> books = getSampleBookDataSet();
		Map<String, DoubleSummaryStatistics> bookPriceStatisticsByGenre = getBookPriceStatisticsByGenre(books);
		displayCountAndMaxAndMinPriceOfBooksByGenre(bookPriceStatisticsByGenre, "Art");
		displayBookDetailsUsingCustomComparator(books, Comparator.comparing(Book::getName));
		displayCountOfBooksBySpecifiedAuthor(books, "Manu S Pillai");
		ArrayList<String> listOfGenre = getListOfGenreUsingCollectionUtils(books);
		displayAllGenre(listOfGenre);
		displayWhetherSpecifiedGenreIsPresent(listOfGenre, "Travel");
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
	
	private static List<Book> getSampleBookDataSet() 
	{
		return Arrays.asList(new Book("Masala Lab", "Krish Ashok", new BigDecimal(399.00), "Food"),
				new Book("The Courtesan, the Mahatma and the Italian Brahman", "Manu S Pillai", new BigDecimal(399.00), "History"),
				new Book("Learning SQL", "Alan Beaulieu", new BigDecimal(1350.0), "Computer Science"),
				new Book("In Our Veins Flow Ink And Fire", "Kochi Biennale Foundation", new BigDecimal(800.00), "Art"),
				new Book("Incarnations", "Sunil Khilnani", new BigDecimal(599.00), "History"),
				new Book("False Allies", "Manu S Pillai", new BigDecimal(899.00), "History"));
	}
	
	private static Map<String, DoubleSummaryStatistics> getBookPriceStatisticsByGenre(List<Book> books) 
	{
		return books.stream().collect(Collectors.groupingBy(Book::getGenre, Collectors.summarizingDouble(b -> b.getPrice().doubleValue())));
	}
	
	private static void displayCountAndMaxAndMinPriceOfBooksByGenre(
			Map<String, DoubleSummaryStatistics> bookPriceStatisticsByGenre, String genre) 
	{
		System.out.print("Number of " + genre + " books in collection = " + bookPriceStatisticsByGenre.get(genre).getCount());
		System.out.print("\tMax price of a " + genre + " book = " + bookPriceStatisticsByGenre.get(genre).getMax());
		System.out.println("\tMin price of a " + genre + " book = " + bookPriceStatisticsByGenre.get(genre).getMin());
	}
	
	private static void displayBookDetailsUsingCustomComparator(List<Book> books, Comparator<Book> comparator) 
	{
		books.stream().sorted(comparator.reversed()).findFirst().ifPresent(System.out::println);
	}
	
	private static void displayCountOfBooksBySpecifiedAuthor(List<Book> books, String author) 
	{
		Map<String, Long> countOfBooksByAuthor = books.stream().collect(Collectors.groupingBy(Book::getAuthor,
				Collectors.collectingAndThen(Collectors.toList(), list -> {
					return list.stream().count();
				})));
		System.out.println("Number of books by " + author + " = " + countOfBooksByAuthor.get(author));
	}
	
	private static ArrayList<String> getListOfGenreUsingCollectionUtils(List<Book> books) 
	{
		Collection<String> collectionOfGenre = CollectionUtils.collect(books, new Transformer<Book, String>() {
			@Override
			public String transform(Book book) {
				return book.getGenre();
			}
		});
		return new ArrayList<>(collectionOfGenre);
	}
	
	private static void displayAllGenre(ArrayList<String> listOfGenre) 
	{
		listOfGenre.stream().distinct().forEach(g -> System.out.print(g + "\t"));
	}
	
	private static void displayWhetherSpecifiedGenreIsPresent(ArrayList<String> listOfGenre, String genre) 
	{
		try {
			System.out.println("\n");
			Preconditions.checkArgument(listOfGenre.contains(genre), "Genre: %s is absent", genre);
			System.out.println(String.format("Genre: %s is present", genre));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
