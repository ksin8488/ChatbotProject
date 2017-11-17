package chat.controller;

import chat.view.PopupDisplay;

public class ChatRunner
{
	public static void main (String [] args)	//can use the terminal to run
	{
		ChatbotController app = new ChatbotController();
		app.start();
		
//		PopupDisplay test = new PopupDisplay();
//		test.displayText("words go here");
//		test.collectResponse("ask a question");
		
	}
}
