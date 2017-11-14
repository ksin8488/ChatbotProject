package chat.view;

import chat.controller.ChatController;
import javax.swing.JFrame;

public class ChatFrame extends JFrame
{
	private ChatController appController;
	private ChatPanel appPane;
	
	public ChatFrame(ChatController appController)
	{
		super();
		this.appController = appController;
		appPane = new ChatPanel(appController);
		
		setupFrame();
	}
	
	private void setupFrame()
	 {
		this.setContentPane(appPane);
		this.setTitle("Chatting with the chatbot");
		this.setResizable(false);;
		this.setSize(600, 600);
		this.setVisible(true);
	 }
}
