package StreetMatcher;
import java.io.*;
import java.util.Arrays;


import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	public static String [][] street = new String[94][6];
	public static String [] street_towed = new String[4735];

	


	@SuppressWarnings("null")
	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		//ReadExcel read = null;
		//if(check == 1){
		
		try {

			//Create a workbook object from the file at specified location.
			//Change the path of the file as per the location on your computer.
			Workbook wrk1 =  Workbook.getWorkbook(new File("src/Street_Closure_Permits_-_Current.xls"));
			Workbook wrk2 =  Workbook.getWorkbook(new File("src/Towed_Vehicles.xls"));

			//Obtain the reference to the first sheet in the workbook
			Sheet sheet1 = wrk1.getSheet(0);
			Sheet sheet2 = wrk2.getSheet(0);
			//Obtain reference to the Cell using getCell(int col, int row) method of sheet
			
			//Read the contents of the Cell using getContents() method, which will return
			//it as a String
			


			//Display the cell contents
			
			for(int i=2 ; i<street.length; i++)
			{
				
				Cell col = sheet1.getCell(0, i);
				Cell col5 = sheet1.getCell(7,i);
				String colname = col.getContents();
				String colType = col5.getContents();
				street[i][0]= colname.toLowerCase();
				street[i][1] = colType;
				}
			
			
			for(int i=1 ; i<street_towed.length; i++)
			{
				Cell col2 = sheet2.getCell(7, i);
				String str_name = col2.getContents();
				street_towed[i]= str_name.toLowerCase();
				}
			

			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			 PrintWriter pr = new PrintWriter("file.txt"); 
		System.out.println("Serial No.\t\tStreet Name\t\t\t\tClosure type");
		System.out.println("---------------------------------------------------------------------------------------");
		pr.println("Serial No.\t\tStreet Name\t\t\t\tClosure type");
		pr.println("---------------------------------------------------------------------------------------");
		boolean flag = false;
		for(int i = 1;i<street_towed.length;i++){
			flag = false;
			for(int j = 2;j<street.length;j++){
				if(street_towed[i].equals(street[j])){
					System.out.println(i+"\t\t"+street_towed[i]+"\t\t\t"+street[j][1]);
					pr.println(i+"\t\t\t"+street_towed[i]+"\t\t\t"+street[j][1]);
					flag = true;
					break;
					
				}
				
				
			}
			if(flag == false){
				System.out.println(i+"\t\t"+street_towed[i]+"\t\t\t"+"No Street full closure");
				pr.println(i+"\t\t\t"+street_towed[i]+"\t\t\t"+"No Street full closure");
			}
		}
		pr.close();
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		    System.out.println("No such file exists.");
		}
		
	}
	}



