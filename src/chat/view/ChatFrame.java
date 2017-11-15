package chat.view;

import chat.controller.ChatController;
import javax.swing.JFrame;

public class ChatFrame extends JFrame
{
	private ChatController appController;
	private ChatPanel appPanel;
	
	public ChatFrame(ChatController appController)
	{
		super();
		this.appController = appController;
		appPanel = new ChatPanel(appController);
		setupFrame();
	}
	
	private void setupFrame()
	 {
		this.setContentPane(appPanel);
		this.setTitle("Chatbot 2017");
		this.setResizable(false);;
		this.setSize(600, 600);
		this.setVisible(true);
	 }
}
