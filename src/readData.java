/*
 * Jordan Orsini ID: 26471196
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class readData 
{
	static ArrayList<Date> dateArray;
	static ArrayList<Double> openArray;
	static ArrayList<Double> highArray;
	static ArrayList<Double> lowArray;
	static ArrayList<Double> closeArray;
	static ArrayList<Double> volumeArray;
	static ArrayList<Double> adjCloseArray;	
	
	public readData() throws NumberFormatException, IOException, ParseException
	{
		String fileName;
		String currentLine;
		BufferedReader myReader;
		boolean firstLoopComplete;
		String[] parsingArray;
		String stringDate;
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		
		//ArrayList<String> dateArray = new ArrayList<String>();
		dateArray = new ArrayList<Date>();
		openArray = new ArrayList<Double>();
		highArray = new ArrayList<Double>();
		lowArray = new ArrayList<Double>();
		closeArray = new ArrayList<Double>();
		volumeArray = new ArrayList<Double>();
		adjCloseArray = new ArrayList<Double>();	
		
		fileName = "Sample data.csv";
		currentLine = "";
		myReader = new BufferedReader(new FileReader(fileName));
		firstLoopComplete = false;
		stringDate = "";
		
		//System.out.println("CSV FILE READER");
		//System.out.println("---------------");
		//System.out.println();
		
		while ((currentLine = myReader.readLine()) != null) 
		{
            if(firstLoopComplete == true)
            {
				//System.out.println(currentLine);
				
				//System.out.println("Parsing...");
	            parsingArray = currentLine.split(",");
	            //System.out.print("Parsing complete...\n");
	            
	            //System.out.println("Parsing array contents: ");
	            for(int x = 0; x < parsingArray.length; x++)
	            {
	            	//System.out.print(parsingArray[x] + "   ");
	            }
	            
	            //System.out.println("\nFilling array lists...");
	            
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
	    		//System.out.println("Filling array lists complete...\n");          
            }
            
    		firstLoopComplete = true;
		}
		
		myReader.close();
		
		Collections.reverse(dateArray);
		Collections.reverse(openArray);
		Collections.reverse(highArray);
		Collections.reverse(lowArray);
		Collections.reverse(closeArray);
		Collections.reverse(volumeArray);
		Collections.reverse(adjCloseArray);
	}
	
	public static ArrayList<Date> GetDateArray()
	{
		return dateArray;
	}
	
	public static ArrayList<Double> GetOpenArray()
	{
		return openArray;
	}
	
	public static ArrayList<Double> GetHighArray()
	{
		return highArray;
	}
	
	public static ArrayList<Double> GetLowArray()
	{
		return lowArray;
	}
	
	public static ArrayList<Double> GetCloseArray()
	{
		return closeArray;
	}
	
	public static ArrayList<Double> GetVolumeArray()
	{
		return volumeArray;
	}
	
	public static ArrayList<Double> GetAdjCloseArray()
	{
		return adjCloseArray;
	}
	
}

