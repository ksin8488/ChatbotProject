package chat.controller;

import chat.model.Chatbot;
import chat.view.PopupDisplay;
import chat.view.ChatFrame;

public class ChatbotController
{
	private Chatbot chatbot;
	private PopupDisplay display;
	private ChatFrame appFrame;

	/**
	 * initializes certain values as well as activate them
	 */
	public ChatbotController()
	{
		chatbot = new Chatbot("Kashish Singh");
		display = new PopupDisplay();
		appFrame = new ChatFrame(this);
	}


	public void start()
	{
		String response = display.collectResponse("What do you want to talk about?");
	}
	
	public String interactWithChatbot(String input)
	{
		String chatbotSays = "";	//MUST have this when having a String
		
		if(chatbot.quitChecker(input))
		{
			close();
		}
		chatbotSays += chatbot.processConversation(input);
		
		return chatbotSays;			//MUST have a return for Strings
	}
	
	/**
	 * Helper Method to close the app after displaying a goodbye
	 */
	private void close()
	{
		display.displayText("Goodbye");
		System.exit(0);		//0 is a correct exit of the system
	}

	/**
	 * The user's response is processed and added to the response which is then returned
	 * @param chat - takes the chat and processes it into processConversation
	 * @return returns the response of the chatbot to the user
	 */
	private String popupChat(String chat)
	{
		String chatbotSays = "";		//assigns a valid value to a variable that will be returned for the method

		chatbotSays += chatbot.processConversation(chat);

		return chatbotSays;
	}
}
