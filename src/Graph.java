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
    public static ArrayList<Date> dates = new ArrayList<Date>();
   

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

        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            
            graphPoints.add(new Point(x1, y1));
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
                g2.drawString("$"+ df.format(yLabel), x0 - labelWidth - 15, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        //ADDED
        int division = 7;
        
        Calendar cal = Calendar.getInstance();
        
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
                    cal.setTime(dates.get(i));
                    //System.out.println(dates.get(i));
                    String myMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
                    if(myMonth.length() == 1)
                    {
                    	myMonth = "0" + myMonth;
                    }
                    g2.drawString(/*xLabel*/ myMonth + "/" + Integer.toString(cal.get(Calendar.YEAR)), x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
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

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
        
        scores.clear();
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
    
    
   //  Graph() {
   static List<Double> createAndShowGui(int x, int mvd, ArrayList<Double> closeValues, ArrayList<Date> dateValues) throws NumberFormatException, IOException, ParseException 
   {
        Random random = new Random();
        List<Double> scores = new ArrayList<>();
        
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
    	int startingGraphIndex = closeValues.size() - maxDataPoints;
    	
    	
    	
    	for(int x2 = 0; x2 < closeValues.size(); x2++)
    	{
    		averageValues.add((double) 0);
    	}
    	
    	
    	
    	//should be days in the gui
    	//int mvd = 20;
    	double total = 0;
    	int u = 0;
    	
    	if(mvd != 0)
    	{
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
    	/*else
    	{
    		//no moving average
    		for(int y = 0; y < closeValues.size(); y++)
    		{
    			averageValues.add(closeValues.get(y));
    		}
    		
    	}*/
    	
        
    	/*for (int i = 0; i < maxDataPoints; i++) 
        {
        	//NOTE: if we have them in chronological order, array[i]
            //scores.add((double) random.nextDouble() * maxScore);
            //scores.add((double) i);
        	 scores.add(averageValues.get(i));
           // scores.add(closeArray.get(i));
        }*/
        dates.clear();
        //scores.clear();
        //trying to fix range
        for(int i = startingGraphIndex; i < closeValues.size(); i++)
        {
        	if(mvd != 0)
        	{
        		scores.add(averageValues.get(i));
        		dates.add(dateValues.get(i));
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