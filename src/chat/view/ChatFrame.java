package chat.view;

import chat.controller.ChatbotController;
import javax.swing.JFrame;

public class ChatFrame extends JFrame
{
	private ChatbotController appController;
	private ChatPanel appPanel;

/**
 * 	Method checks if the app has run then loads the setupFrame() constructor
 * @param appController Looks for the program to run
 */
	public ChatFrame(ChatbotController appController)
	{
		super();
		this.appController = appController;
		appPanel = new ChatPanel(appController);

	
		setupFrame();
	}
	
	/**
	 * Constructor to setup the values for the frame
	 */
	private void setupFrame()
	 {
		this.setContentPane(appPanel);
		//this.setAppController(appController);
		this.setTitle("Chatting with the Chatbot");
		this.setResizable(false);
		this.setSize(600, 600);
		this.setVisible(true);
	 }
}
