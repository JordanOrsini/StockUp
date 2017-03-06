/*
 * Jordan Orsini ID: 26471196
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main 
{
	public static void main(String[] args) throws IOException, ParseException 
	{
		String fileName;
		String currentLine;
		BufferedReader myReader;
		boolean firstLoopComplete;
		String[] parsingArray;
		String stringDate;
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd");
		Date date;
		
		//ArrayList<String> dateArray = new ArrayList<String>();
		ArrayList<Date> dateArray = new ArrayList<Date>();
		ArrayList<Double> openArray = new ArrayList<Double>();
		ArrayList<Double> highArray = new ArrayList<Double>();
		ArrayList<Double> lowArray = new ArrayList<Double>();
		ArrayList<Double> closeArray = new ArrayList<Double>();
		ArrayList<Double> volumeArray = new ArrayList<Double>();
		ArrayList<Double> adjCloseArray = new ArrayList<Double>();	
		
		fileName = "Sample data.csv";
		currentLine = "";
		myReader = new BufferedReader(new FileReader(fileName));
		firstLoopComplete = false;
		stringDate = "";
		
		System.out.println("CSV FILE READER");
		System.out.println("---------------");
		System.out.println();
		
		while ((currentLine = myReader.readLine()) != null) 
		{
            if(firstLoopComplete == true)
            {
				//System.out.println(currentLine);
				
				System.out.println("Parsing...");
	            parsingArray = currentLine.split(",");
	            System.out.print("Parsing complete...\n");
	            
	            System.out.println("Parsing array contents: ");
	            for(int x = 0; x < parsingArray.length; x++)
	            {
	            	System.out.print(parsingArray[x] + "   ");
	            }
	            
	            System.out.println("\nFilling array lists...");
	            
	            //Converts String into Date object
	            stringDate = parsingArray[0]; 
	            date = (Date)dateFormatter.parse(stringDate);
	            
	            dateArray.add(date);
	            openArray.add(Double.parseDouble(parsingArray[1]));
	    		highArray.add(Double.parseDouble(parsingArray[2]));
	    		lowArray.add(Double.parseDouble(parsingArray[3]));
	    		closeArray.add(Double.parseDouble(parsingArray[4]));
	    		volumeArray.add(Double.parseDouble(parsingArray[5]));
	    		adjCloseArray.add(Double.parseDouble(parsingArray[6]));	
	    		System.out.println("Filling array lists complete...\n");          
            }
            
    		firstLoopComplete = true;
		}
		
		myReader.close();
		
		System.out.println("Data arrays ready...");
		System.out.println("Data array contents: ");
		for(int y = 0; y < dateArray.size(); y++)
		{
			System.out.println(dateFormatter.format(dateArray.get(y)) + "   " + openArray.get(y) + "   " + highArray.get(y) + "   " + lowArray.get(y) + "   " + closeArray.get(y) + "   " + volumeArray.get(y) + "   " + adjCloseArray.get(y));
		}
		
		System.out.println("\nEnd.");
	}
}

