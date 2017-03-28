
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map; 

public class YahooTest {
 
  public static void main(String[] args) throws IOException {
   
	  /*YahooFinance yahoo = new YahooFinance();
    Stock stock = yahoo.get("GOOG");
    StockQuote sq = stock.getQuote();
 
    System.out.println("Symbol: " + sq.getSymbol());
    System.out.println("Price: " + sq.getPrice());
    System.out.println("Date: " + sq.getLastTradeTime().getTime());
    */
	  
	  String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
	  Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
	  Stock intel = stocks.get("INTC");
	  Stock airbus = stocks.get("AIR.PA");
	  intel.print();
	  airbus.print();
	  
  }
}