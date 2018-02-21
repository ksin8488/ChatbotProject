package chat.view;

import javax.swing.*;

import chat.controller.ChatbotController;
import java.awt.Color;
import javax.swing.SpringLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates the panel for the GUI and manipulates objects and layout as well as interaction within the panel
 * @author ksin8488
 *
 */
public class ChatPanel extends JPanel
{
	private ChatbotController appController;
	private JButton chatButton;
	private JButton searchButton;
	private JButton saveButton;
	private JButton loadButton;
	private JButton tweetButton;
	private JTextField inputField;
	private JTextArea chatArea;
	private SpringLayout appLayout;
	private JButton checkerButton;
	private JLabel infoLabel;
	//Need a data member for the scrollpane
	private JScrollPane chatScrollPane;
	
	/**
	 * Initializes GUI data members then calls the methods when the app runs
	 * @param appController checks if the app ran
	 */
	public ChatPanel(ChatbotController appController)
	{
		super();
		this.appController = appController;
		
		//Initialize GUI data members
		chatButton = new JButton("chat", new ImageIcon(getClass().getResource("/chat/view/images/chat.png")));
		loadButton = new JButton("load", new ImageIcon(getClass().getResource("/chat/view/images/load.png")));
		saveButton = new JButton("save", new ImageIcon(getClass().getResource("/chat/view/images/save.png")));
		tweetButton = new JButton("tweet", new ImageIcon(getClass().getResource("/chat/view/images/tweet.png")));
		searchButton = new JButton("search", new ImageIcon(getClass().getResource("/chat/view/images/search.png")));
		chatArea = new JTextArea(10, 25);
		inputField = new JTextField(20);
		appLayout = new SpringLayout();
		appLayout.putConstraint(SpringLayout.NORTH, searchButton, 0, SpringLayout.NORTH, tweetButton);
		appLayout.putConstraint(SpringLayout.WEST, searchButton, 0, SpringLayout.WEST, loadButton);
		appLayout.putConstraint(SpringLayout.NORTH, tweetButton, 12, SpringLayout.SOUTH, chatButton);
		appLayout.putConstraint(SpringLayout.WEST, tweetButton, 0, SpringLayout.WEST, chatButton);
		appLayout.putConstraint(SpringLayout.NORTH, saveButton, 0, SpringLayout.NORTH, chatButton);
		appLayout.putConstraint(SpringLayout.WEST, saveButton, 6, SpringLayout.EAST, loadButton);
		appLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, chatButton);
		appLayout.putConstraint(SpringLayout.WEST, loadButton, 24, SpringLayout.EAST, chatButton);
		appLayout.putConstraint(SpringLayout.SOUTH, inputField, -32, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.SOUTH, chatButton, -132, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.EAST, chatButton, -238, SpringLayout.EAST, this);
		checkerButton = new JButton("check contents");
		infoLabel = new JLabel("Type to chat with the chatbot");
		//init the scrollpane
		chatScrollPane = new JScrollPane();
		
		
		
		//Method Calls
		setupScrollPane();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
/*
 * Sets up the scroll Pane
 */
	private void setupScrollPane()
	{
		chatScrollPane.setViewportView(chatArea);
		chatScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	//Never see a horizontal scroll bar
		chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);	//See vertical bar when you need to
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
	}
	
	/**
	 * Set's the data members and ensures users can't access certain fields
	 */
	private void setupPanel()	//install layout manager and components
	{
		this.setBackground(Color.orange);
		this.setLayout(appLayout);
		this.add(chatButton);
		this.add(loadButton);
		this.add(saveButton);
		this.add(tweetButton);
		this.add(searchButton);
		this.add(inputField);
		this.add(chatScrollPane);
		this.add(checkerButton);
		chatArea.setEnabled(false);		//makes it so user can't enable it
		chatArea.setEditable(false);		//makes it so user can't type into it
		
	}
	
	/**
	 * Sets up the layout of the windows
	 */
	private void setupLayout()
	{
		appLayout.putConstraint(SpringLayout.NORTH, chatScrollPane, 60, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, chatScrollPane, 65, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.EAST, chatScrollPane, -65, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.WEST, inputField, 0, SpringLayout.WEST, chatScrollPane);
		appLayout.putConstraint(SpringLayout.NORTH, checkerButton, 6, SpringLayout.SOUTH, chatScrollPane);
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
		
		tweetButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent click)
				{
						appController.tweet(inputField.getText());
				}
			});
		
		searchButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent click)
				{
					
				}
			});
		
		saveButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent click)
				{
					
				}
			});
		
		loadButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent click)
				{
					
				}
			});
	
	}
}
