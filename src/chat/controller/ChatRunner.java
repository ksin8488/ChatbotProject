package chat.controller;

import chat.view.PopupDisplay;

public class ChatRunner
{
	public static void main (String [] args)
	{
		ChatController app = new ChatController();
		app.start();
		PopupDisplay test = new PopupDisplay();
		test.displayText("words go here");
		test.collectResponse("ask a question");
		
	}
}
