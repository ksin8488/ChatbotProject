package chat.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

public class IOController
{
	public static void SaveToFile(ChatbotController app, String textToSave, String path)
	{
		
	}
	
	public static String loadfromFile(ChatbotController app, String filename)
	{
		String results = "";
		
		try
		{
			//Opens at root level of project
			//AKA in the Chatbot2017 folder
			
			File openFile = new File(filename);
			Scanner fileScanner = new Scanner(openFile);
			
			String currentLine = fileScanner.nextLine();
			while(fileScanner.hasNextLine())
			{
				results += currentLine + "\n";
				currentLine = fileScanner.nextLine();
			}
			results += currentLine + "\n";
			fileScanner.close();
		}
		catch(IOException error)
		{
			app.handleErrors(error);
		}
		
		return results;
	}
}
