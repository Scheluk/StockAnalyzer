package stockanalyzer.ctrl;

import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.Result;
import yahooApi.beans.YahooResponse;

import java.util.*;

public class Controller {
	public void process(String ticker) {
		System.out.println("Start process");
		//1) Daten laden
		QuoteResponse data = (QuoteResponse) getData(ticker);
		//2) Daten Analyse
		try {
			data.getResult().stream().forEach(quote -> System.out.println(quote.getSymbol() + ": " + quote.getAsk()));
			System.out.println();

			System.out.println("Highest value: " + data.getResult().stream().mapToDouble(Result::getAsk).max().orElseThrow(null));
			System.out.println("Average value: " + data.getResult().stream().mapToDouble(Result::getAsk).average().orElseThrow(null));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}
	

	public Object getData(String searchString) {
		try {
			List<String> tickerStringList = Arrays.asList(searchString.split(";"));
			YahooFinance yahooFinance = new YahooFinance();
			YahooResponse yahooResponse = yahooFinance.getCurrentData(tickerStringList);
			return yahooResponse.getQuoteResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public void closeConnection() {

	}
}
