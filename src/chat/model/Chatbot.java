package chat.model;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Chatbot
{
	private List<Movie> movieList;
	private List<String> shoppingList;
	private List<String> cuteAnimalMemes;
	private String [] verbs;
	private String [] topics;
	private String [] followUps;
	private String [] questions;
	private String username;
	private String content;
	private String intro;
	private LocalTime currentTime;
	
	public Chatbot(String username)	//all are objects
	{
		this.movieList = new ArrayList<Movie>();		//null
		this.shoppingList = new ArrayList<String>();
		this.cuteAnimalMemes = new ArrayList<String>();
		this.currentTime = null;
		this.questions = new String [10];		//null
		this.username = username;
		this.content = null;
		this.intro = null;
		this.currentTime = null;
		this.topics = new String [4];		//null
		this.verbs = new String [4];
		this.followUps = new String [4];		//null
		
		buildVerbs();
		buildTopics();
		buildFollowUps();
		buildMovieList();
		buildShoppingList();
		buildQuestions();
	}
	
	private void buildVerbs()
	{
		verbs[0] = "like";
		verbs[1] = "dislike";
		verbs[2] = "am ambivalent about";
		verbs[3] = "am thinking about";
	}
	
	private void buildTopics()
	{
		
	}
	
	private void buildFollowUps()
	{
		
	}
	
	private void buildMovieList()
	{
		movieList.add(new Movie ("Spider-man: Homecoming", "Science Fiction", "pg-13", "92%", 133, LocalDate.of(2017, 06, 28), 4));		//("Spider-man: Homecoming", "Science Fiction", "pg-13", "92%", 133, LocalDate.of(2017, 06, 28), 4);
	}
	
	private void buildShoppingList()
	{
		shoppingList.add("snacks");
		shoppingList.add("veggies");
		shoppingList.add("protein");
//		shoppingList.add("slug bait");	//WILL FAIL A TEST LATER
		shoppingList.add("gross things");
		shoppingList.add("ice cream");
	}
	
	private void buildCuteAnimals()
	{
		
	}
	
	private void buildQuestions()	//creates and adds new strings (questions for the user) into the questions array
	{
		questions[0] = "What is your name? ";
		questions[1] = "What do you like to do? ";
		questions[2] = "What do you hate doing? ";
		questions[3] = "What is your favorite movie? ";
		questions[4] = "What is your favorite pie? ";
		questions[5] = "What is your favorite holiday? ";
		questions[6] = "What is your favorite pet? ";
		questions[7] = "What is your favorite flavor of tree? ";
		questions[8] = "What is your favorite ocean current? ";
		questions[9] = "What is your favorite bean type? ";
	}
	
	public String processConversation(String input)		//creates a response for the chatbot to use that takes the user's input and uses it to create a response
	{
		String chatbotResponse = "";
		chatbotResponse += "You said:" + "\n" + input + "\n";
		
		chatbotResponse += buildChatbotResponse();
		
		return chatbotResponse;
	}
	
	private String buildChatbotResponse()		//creates a response for the chatbot that randomly selects a verb, topic, and question to ask the user
	{
		String response = "I ";
		int random = (int) (Math.random() * verbs.length);
		
		response += verbs[random];
		
		random = (int) (Math.random() * topics.length);
		response += " " +topics[random] + ".\n";
		
		random = (int) (Math.random() * questions.length);
		response += questions[random];
		
		return response;
	}
	
	public boolean lengthChecker(String input)	//.length() > 2    ! = null
	{
		boolean validLength = false;
		
		if(input != null)		//== is used with primitives but can also be used with null
		{
			if(input.length() > 2)
			{
				validLength = true;
			}
		}
		
		//Sequential if
		/*
		 * if (input != null)
		 * {
		 * 		validLength = true;
		 * }
		 * 
		 * if (input.length > 2)
		 * {
		 * 		validLength = true;
		 * }
		 * else
		 * {
		 * 		validLength = false;
		 * }
		 */
		
		//Compound if
		/*
		 * if (input != null && input.length() > 2)		the && operator requieres both to be correct so null can be used
		 * {												however, null will not work with || (the OR operator)
		 * 		validLength = true
		 * }
		 */
	
		return validLength;
		//All of these if statements are the same to the compiler. So they don't take up more or less. The only difference is programmer preference
	}
	
	public boolean htmlTagChecker(String input)
	{
		return false;
	}
	
	public boolean userNameChecker(String input)
	{
		return false;
	}
	
	public boolean contentChecker(String contentCheck)
	{
		return false;
	}
	
	public boolean cuteAnimalMemeChecker(String input)
	{
		return false;
	}
	
	public boolean shoppingListChecker(String shoppingItem)	//looks through the shoppingList list to see if it contains the correct shoppingItem
	{
			if(shoppingList.contains(shoppingItem))
			{
				return true;
			}
			else
				return false;	
	}
	
	public boolean movieTitleChecker(String title)
	{
//		if movieTitle != ("") && movieTitle.equalsIgnoreCase ("Spiderman") && movieTitle.equalsIgnoreCase ("Hidden Figures")
//				{
//					return true;
//				}
		return false;
	}
	
	public boolean movieGenreChecker(String genre)
	{
		return false;
	}

	public boolean quitChecker(String exitString)		//checks if the user typed in quit (casing doesn't matter) and returns true if they did AND if it isn't a null
	{
		if (exitString != (null) && exitString.equalsIgnoreCase("quit"))
		{
			return true;
		}

		return false;
	}

	public boolean keyboardMashChecker(String sample)
	{
		return false;
	}
	
	public List<Movie> getMovieList()
	{
		return movieList;
	}
	
	public List<String> getShoppingList()
	{
		return shoppingList;
	}
	
	public List<String> getCuteAnimalMemes()
	{
		return cuteAnimalMemes;
	}

	public String [] getQuestions()
	{
		return questions;		//null
	}
	
	public String[] getVerbs()
	{
		return verbs;
	}

	public String[] getTopics()
	{
		return topics;
	}

	public String[] getFollowUps()
	{
		return followUps;
	}

	public String getUsername()
	{
		return username;
	}
	
	public String getContent()
	{
		return content;
	}

	public String getIntro()
	{
		return null;
	}
	
	public LocalTime getCurrentTime()
	{
		return null;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
}
