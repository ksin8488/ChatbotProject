package chat.controller;

import chat.model.Chatbot;
import chat.view.PopupDisplay;
import chat.view.ChatFrame;

public class ChatController
{
	private Chatbot chatbot;
	private PopupDisplay display;
	private ChatFrame appFrame;

	public ChatController()
	{
		chatbot = new Chatbot("Kashish Singh");
		display = new PopupDisplay();
		appFrame = new ChatFrame(this);
	}

	public void start()
	{
		String response = display.collectResponse("What do you want to talk about?");

//		while (chatbot.lengthChecker(response) && !chatbot.quitChecker(response))	//keeps going as long as the length is greater than 2 and the user doesn't type "quit"
//		{
//			response = popupChat(response);					//sends to popupChat which then returns a new response
//			response = display.collectResponse(response);	//
//		}
	}

	private String popupChat(String chat)
	{
		String chatbotSays = "";		//assigns a valid value to a variable that will be returned for the method

		chatbotSays += chatbot.processConversation(chat);

		return chatbotSays;
	}
}
