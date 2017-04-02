import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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


//figure out how to get pairs?
//moving average will be calculated
//20,50,100 200 days	
//stock's history in increments of 1, 2, and 5 years

//2x2 array -> pair[date,value]

public class Graph extends JPanel {

	List<Point> graphPoints = new ArrayList<>();


    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int numberYDivisions = 10;
    private List<Double> scores;
    public static ArrayList<String> dates = new ArrayList<String>();
    public static ArrayList<Double> scores2 = new ArrayList<Double>();
    public static ArrayList<Double> scores3 = new ArrayList<Double>();
   

    public Graph(List<Double> scores) {
        this.scores = scores;
    }
    public Graph() {
	}
 
 
    
   
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());
        List<Point> graphPoints = new ArrayList<>();
        List<Point> graphPoints2 = new ArrayList<>();
        List<Point> graphPoints3 = new ArrayList<>();
        List<Point> graphPointsBuy = new ArrayList<>();
        List<Point> graphPointsSell = new ArrayList<>();

        for (int i = 0; i < scores.size(); i++) 
        {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            int y2 = 0;
            int y3 = 0;
            graphPoints.add(new Point(x1, y1));
           
           
            if(scores2.size() > 0)
            {
            	if(scores2.get(i) != 0)
            	{
            		y2 = (int) ((getMaxScore() - scores2.get(i)) * yScale + padding);
            		graphPoints2.add(new Point(x1, y2));
            	}
            }
            
            if(scores3.size() > 0)
            {
            	if(scores3.get(i) != 0)
            	{
            		y3 = (int) ((getMaxScore() - scores3.get(i)) * yScale + padding);
            		graphPoints3.add(new Point(x1, y3));
            		
            		if(scores2.size() > 0)
            		{
            			if(scores2.get(i) != 0)
            			{
            				//if(y2 == y3)
            				if(almostEqual(y2, y3, 2.5) == true)
            				{
            					if(scores2.get(i-1) > scores3.get(i-1))
            					{
            						graphPointsSell.add(new Point(x1,y2 - 1));
            					}
            					else if(scores2.get(i-1) < scores3.get(i-1))
            					{
            						graphPointsBuy.add(new Point(x1,y3 + 1));
            						
            					}
            					
            				}
            			}
            		}
            	}
            }
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);
        
        DecimalFormat df = new DecimalFormat(".#");
        df.setRoundingMode(RoundingMode.CEILING);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                Double yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0;
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(df.format(yLabel));
                g2.drawString("$"+ yLabel.intValue()/*df.format(yLabel)*/, x0 - labelWidth - 7, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        //ADDED
        int division = 11;
        
        //Calendar cal = Calendar.getInstance();
        
        // and for x axis
        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                //REPLACED
                //if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
                if ((i % (((scores.size() / division)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    //i+1 for the days => march 1-20 will be 0-19 in i's
                    String xLabel = i+1 + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                   // cal.setTime(dates.get(i));
                    //System.out.println(dates.get(i));
                    //String myMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
                    //if(myMonth.length() == 1)
                    //{
                    //	myMonth = "0" + myMonth;
                    //}
                    //g2.drawString(/*xLabel*/ myMonth + "/" + Integer.toString(cal.get(Calendar.YEAR)), x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                    g2.drawString(dates.get(i), x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                    //SWITCHED PART1
                    g2.drawLine(x0, y0, x1, y1);
                }
                //SWITCHED PART2
                //g2.drawLine(x0, y0, x1, y1);
            }
        }

        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        //LINE 1 HISTORICAL
        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

       /* g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }*/
        
        if(scores2.size() > 0)
        {
        	//LINE 2 SHORT TERM MOVING AVERAGE
        	g2.setColor(Color.ORANGE);
        	g2.setStroke(GRAPH_STROKE);
        	for (int i = 0; i < graphPoints2.size() - 1; i++) 
        	{
        		int x1 = graphPoints2.get(i).x;
        		int y1 = graphPoints2.get(i).y;
        		int x2 = graphPoints2.get(i + 1).x;
        		int y2 = graphPoints2.get(i + 1).y;
        		g2.drawLine(x1, y1, x2, y2);
        	}
        
        	/*g2.setStroke(oldStroke);
        	g2.setColor(pointColor);
        	for (int i = 0; i < graphPoints2.size(); i++) 
        	{
        		int x = graphPoints2.get(i).x - pointWidth / 2;
        		int y = graphPoints2.get(i).y - pointWidth / 2;
        		int ovalW = pointWidth;
        		int ovalH = pointWidth;
        		g2.fillOval(x, y, ovalW, ovalH);
        	}*/
        }
        
        if(scores3.size() > 0)
        {
        	//LINE 3 LONG TERM MOVING AVERAGE
        	g2.setColor(Color.MAGENTA);
        	g2.setStroke(GRAPH_STROKE);
        	for (int i = 0; i < graphPoints3.size() - 1; i++) 
        	{
        		int x1 = graphPoints3.get(i).x;
        		int y1 = graphPoints3.get(i).y;
        		int x2 = graphPoints3.get(i + 1).x;
        		int y2 = graphPoints3.get(i + 1).y;
        		g2.drawLine(x1, y1, x2, y2);
        	}
        
        	/*g2.setStroke(oldStroke);
        	g2.setColor(pointColor);
        	for (int i = 0; i < graphPoints3.size(); i++) 
        	{
        		int x = graphPoints3.get(i).x - pointWidth / 2;
        		int y = graphPoints3.get(i).y - pointWidth / 2;
        		int ovalW = pointWidth;
        		int ovalH = pointWidth;
        		g2.fillOval(x, y, ovalW, ovalH);
        	}*/
        }
        
        //BUY POINTS
        	/*g2.setColor(Color.GREEN);
        	g2.setStroke(GRAPH_STROKE);
        	for (int i = 0; i < graphPointsBuy.size() - 1; i++) 
        	{
        		int x1 = graphPointsBuy.get(i).x;
        		int y1 = graphPointsBuy.get(i).y;
        		int x2 = graphPointsBuy.get(i + 1).x;
        		int y2 = graphPointsBuy.get(i + 1).y;
        		g2.drawLine(x1, y1, x2, y2);
        	}*/
        
        	g2.setStroke(oldStroke);
        	g2.setColor(Color.GREEN);
        	for (int i = 0; i < graphPointsBuy.size(); i++) 
        	{
        		int x = graphPointsBuy.get(i).x - pointWidth / 2;
        		int y = graphPointsBuy.get(i).y - pointWidth / 2;
        		int ovalW = pointWidth;
        		int ovalH = pointWidth;
        		//g2.fillOval(x, y, ovalW, ovalH);
        		// g2.drawPolygon(new int[] {10, 20, 30}, new int[] {100, 20, 100}, 3);
        		 g2.fillPolygon(new int[] {graphPointsBuy.get(i).x+5, graphPointsBuy.get(i).x, graphPointsBuy.get(i).x-5}, new int[] {graphPointsBuy.get(i).y+25, graphPointsBuy.get(i).y, graphPointsBuy.get(i).y+25}, 3);
        	}
        	
        	//SELL POINTS
        	/*g2.setColor(Color.RED);
        	g2.setStroke(GRAPH_STROKE);
        	for (int i = 0; i < graphPointsSell.size() - 1; i++) 
        	{
        		int x1 = graphPointsSell.get(i).x;
        		int y1 = graphPointsSell.get(i).y;
        		int x2 = graphPointsSell.get(i + 1).x;
        		int y2 = graphPointsSell.get(i + 1).y;
        		g2.drawLine(x1, y1, x2, y2);
        	}*/
        
        	g2.setStroke(oldStroke);
        	g2.setColor(Color.RED);
        	for (int i = 0; i < graphPointsSell.size(); i++) 
        	{
        		int x = graphPointsSell.get(i).x - pointWidth / 2;
        		int y = graphPointsSell.get(i).y - pointWidth / 2;
        		int ovalW = pointWidth;
        		int ovalH = pointWidth;
        		//g2.fillOval(x, y, ovalW, ovalH);
        		//g2.fillPolygon(new int[] {x+5, x, x-5}, new int[] {y+25, y, y+25}, 3);
        		g2.fillPolygon(new int[] {graphPointsSell.get(i).x+5, graphPointsSell.get(i).x, graphPointsSell.get(i).x-5}, new int[] {graphPointsSell.get(i).y-25, graphPointsSell.get(i).y, graphPointsSell.get(i).y-25}, 3);
        	}
        
        scores.clear();
        scores2.clear();
        scores3.clear();
    }

    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }

    public List<Double> getScores() {
        return scores;
    }
    
	public static boolean almostEqual(double a, double b, double eps)
	{
	    return Math.abs(a-b) < eps;
	}
    
    
   //  Graph() {
   static List<Double> createAndShowGui(int x, int mvd, ArrayList<Double> closeValues, ArrayList<String> dateValues, int mvd2) throws NumberFormatException, IOException, ParseException 
   {
        Random random = new Random();
        ArrayList<Double> scores = new ArrayList<Double>();
        
    	/*double[] average = new double[x];
    	 for (int i = 0; i < x; i++) {
    		 average[i] = (double)(Math.random()*x);
    	 }*/
        
        int maxDataPoints = x;// NOTE: this is where we will set the total days
        
        
        //READ FROM FILE
        /*Main readFromFile = new Main();     
        ArrayList<Double> closeValues = new ArrayList<Double>();
    	ArrayList<Date> dayValues = new ArrayList<Date>();
    	closeValues = readFromFile.GetCloseArray();
    	dayValues = readFromFile.GetDateArray();*/
    	
        ArrayList<Double> averageValues = new ArrayList<Double>();
        ArrayList<Double> averageValues2 = new ArrayList<Double>();
    	int startingGraphIndex = closeValues.size() - maxDataPoints;
    	
    	//should be days in the gui
    	//int mvd = 20;
    	double total = 0;
    	int u = 0;
    	
    	if(mvd != 0)
    	{
    		for(int x2 = 0; x2 < closeValues.size(); x2++)
        	{
        		averageValues.add((double) 0);
        	}
    		
    		//moving average calculation
    		for(int y = 0; y < closeValues.size(); y++)
	    	{
	    		//total = 0;
	    		if(y+1 >= mvd)
	    		{
	    			total = 0;
	    			u = y;
	    			for(int z = mvd; z > 0 ; z--)
	    			{
	    				total = total + closeValues.get(u);
	    				//System.out.println(z + " " + total);
	    				u--;
	    			}
	    			averageValues.add(y, (double)total/mvd);
	    		}
	    	}
    	}
    	
    	if(mvd2 != 0)
    	{
    		for(int x2 = 0; x2 < closeValues.size(); x2++)
        	{
        		averageValues2.add((double) 0);
        	}
    		
    		//moving average calculation
    		for(int y = 0; y < closeValues.size(); y++)
	    	{
	    		//total = 0;
	    		if(y+1 >= mvd2)
	    		{
	    			total = 0;
	    			u = y;
	    			for(int z = mvd2; z > 0 ; z--)
	    			{
	    				total = total + closeValues.get(u);
	    				//System.out.println(z + " " + total);
	    				u--;
	    			}
	    			averageValues2.add(y, (double)total/mvd2);
	    		}
	    	}
    	}
    	
        dates.clear();
        scores2.clear();
        scores3.clear();
        //scores.clear();
        //trying to fix range
        for(int i = startingGraphIndex; i < closeValues.size(); i++)
        {
        	if(mvd != 0)
        	{
        		scores.add(closeValues.get(i));
        		dates.add(dateValues.get(i));
        		scores2.add(averageValues.get(i));
        		
        		if(mvd2 != 0)
            	{
            		scores3.add(averageValues2.get(i));
            	}
        	}
        	else if(mvd2 != 0)
        	{
        		scores.add(closeValues.get(i));
        		dates.add(dateValues.get(i));
        		scores3.add(averageValues2.get(i));
        	}
        	else
        	{
        		scores.add(closeValues.get(i));
        		dates.add(dateValues.get(i));
        	}
        }
        return scores;
    }

}