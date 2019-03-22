package spo;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class lab6 {
	static boolean fileFound;
	public static void searchFile(File f, String fileName)     //File f is "C:\\"
	{  
	   try
	   {
	    if(f.isDirectory())
	    {
	    File [] fi = f.listFiles();
	    for(int i=0;i<fi.length;i++)
	    {
	    if(fileFound==true) 
	    {
	      break;
	    }  
	    System.out.println(fi[i].getName());
	    searchFile(fi[i],fileName);
	    }
	    }
	    else
	    {
	    if(f.getName().equalsIgnoreCase(fileName) ||  f.getName().toLowerCase().startsWith(fileName.toLowerCase())||(f.getName().toLowerCase().endsWith(fileName.toLowerCase())))
	    {    
	    System.out.print("file found " + f.getAbsolutePath()); 
	    fileFound=true;
	    if (Desktop.isDesktopSupported()) {
	        Desktop.getDesktop().edit(f);
	    }
	    }
	    }
	   }
	    catch(Exception e)
	      {
	      }
	 }
	 public static void main(String[] args) throws IOException   {
		 System.out.println("Enter filename: ");
		 Scanner scan= new Scanner(System.in);
		 String text= scan.nextLine();
		 searchFile(new File("c:\\"), text);
	}
}
