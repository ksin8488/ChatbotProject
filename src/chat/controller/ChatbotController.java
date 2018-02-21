package chat.controller;

import chat.model.Chatbot;
import chat.view.PopupDisplay;
import chat.view.ChatFrame;
import chat.model.CTECTwitter;	//so we can use the CTECTwitter class

/**
 * Manages the Chatbot application including the Model and Frame of the View package.
 * @author Kashish Singh
 * @version 21.11.17 Added Frame 1.3
 */
public class ChatbotController
{
	private Chatbot chatbot;
	private PopupDisplay display;
	private ChatFrame appFrame;
	private CTECTwitter myTwitter;

	/**
	 * initializes certain values as well as activate them
	 */
	public ChatbotController()
	{
		chatbot = new Chatbot("Kashish Singh");
		myTwitter = new CTECTwitter(this);	//comes BEFORE the view
		display = new PopupDisplay();
		appFrame = new ChatFrame(this);
	}

	/**
	 * Displays the first text from the chatBot and prepares to collectResponse and store it as a string called "response"
	 */
	public void start()
	{
		String response = display.collectResponse("What do you want to talk about?");
	}
	
	/**
	 * Takes in the user's response and checks if it say to quit. If not then it creates a response and returns it
	 * @param input - user's response
	 * @return the response of the Chatbot from combining chatbotSays and processConversation with the input
	 */
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
	
	public String useCheckers(String text)
	{
		String response = "";
		
		if(chatbot.contentChecker(text))
		{
			response += "This text matches the special content\n";
		}
		if(chatbot.cuteAnimalMemeChecker(text))
		{
			response += "";
		}
		//continue with all checkers except length and quit checker
		
		return response;
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
