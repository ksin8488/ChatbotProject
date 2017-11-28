package chat.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import chat.controller.ChatbotController;
import java.awt.Color;
import javax.swing.SpringLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChatPanel extends JPanel
{
	private ChatbotController appController;
	private JButton chatButton;
	private JTextField inputField;
	private JTextArea chatArea;
	private SpringLayout appLayout;
	private JButton checkerButton;
	private JLabel infoLabel;
	
	/**
	 * Initializes GUI data members then calls the methods when the app runs
	 * @param appController checks if the app ran
	 */
	public ChatPanel(ChatbotController appController)
	{
		super();
		this.appController = appController;
		
		//Initialize GUI data members
		chatButton = new JButton("chat");
		chatArea = new JTextArea(10, 25);
		inputField = new JTextField(20);
		appLayout = new SpringLayout();
		checkerButton = new JButton("check");
		infoLabel = new JLabel("Type to chat with the chatbot");
		
		//Method Calls
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	/**
	 * Set's the data members and ensures users can't access certain fields
	 */
	private void setupPanel()	//install layout manager and components
	{
		this.setBackground(Color.orange);
		this.setLayout(appLayout);
		this.add(chatButton);
		this.add(inputField);
		this.add(chatArea);
		this.add(checkerButton);
		chatArea.setEnabled(false);		//makes it so user can't enable it
		chatArea.setEditable(false);		//makes it so user can't type into it
		
	}
	
	/**
	 * Sets up the layout of the windows
	 */
	private void setupLayout()
	{
		appLayout.putConstraint(SpringLayout.NORTH, chatArea, 60, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, chatArea, 65, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.EAST, chatArea, -65, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.NORTH, inputField, 0, SpringLayout.NORTH, chatButton);
		appLayout.putConstraint(SpringLayout.SOUTH, chatButton, -10, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.EAST, chatButton, 0, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.WEST, inputField, 0, SpringLayout.WEST, chatArea);
		appLayout.putConstraint(SpringLayout.NORTH, checkerButton, 6, SpringLayout.SOUTH, chatArea);
		appLayout.putConstraint(SpringLayout.WEST, checkerButton, 183, SpringLayout.WEST, this);
	}
	
	/**
	 * Allows inputs to go into the interaction with the chatbot to be processed from the window
	 */
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()		//Button sends the user's response to be processed
		{
			public void actionPerformed(ActionEvent click)
			{
				String userText = inputField.getText();
				String displayText = appController.interactWithChatbot(userText);
				chatArea.append(displayText);	//append adds to the end of the page
				inputField.setText("");
			}
		});
		
		checkerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String userText = inputField.getText();
				String displayText = appController.useCheckers(userText);
				chatArea.append(displayText);;
				inputField.setText("");
			}
		});
	
	}
}
