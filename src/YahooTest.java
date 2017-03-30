
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockQuote;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map; 

public class YahooTest 
{
 
  public static void main(String[] args) throws IOException 
  {
   
	/*YahooFinance yahoo = new YahooFinance();
    Stock stock = yahoo.get("GOOG");
    StockQuote sq = stock.getQuote();
 
    System.out.println("Symbol: " + sq.getSymbol());
    System.out.println("Price: " + sq.getPrice());
    System.out.println("Date: " + sq.getLastTradeTime().getTime());
    */
	  
	/*String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
  	Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
  	Stock intel = stocks.get("INTC");
  	Stock airbus = stocks.get("AIR.PA");
  	intel.print();
  	airbus.print();*/
	  
	  Calendar from = Calendar.getInstance();
	  Calendar to = Calendar.getInstance();
	  Calendar cal = Calendar.getInstance();
	  
	  from.add(Calendar.YEAR, -1000);
	  //Stock apple = YahooFinance.get("AAPL", from, to, Interval.DAILY);
	  Stock apple = YahooFinance.get("AAPL");
	  List<HistoricalQuote> appleHistory = apple.getHistory(from, to, Interval.DAILY);
	  
	  for(int x = 0; x < appleHistory.size(); x++)
	  {
		  cal = appleHistory.get(x).getDate();
		  
		  String myMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
          
		  if(myMonth.length() == 1)
          {
          	myMonth = "0" + myMonth;
          }
		  
		  String myYear = Integer.toString(cal.get(Calendar.YEAR));
		  
		  System.out.println(myMonth + "/" + myYear);
		  System.out.println(appleHistory.get(x).getClose());
		  System.out.println();
	  }
	 // apple.print();
	 //System.out.println(apple.getHistory(from, to, Interval.DAILY));
	  
  }
}