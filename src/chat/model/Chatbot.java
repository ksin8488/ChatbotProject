package chat.model;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Generates and builds topics for the chatbot to use and tests those topics to see if they are valid
 * @author Kashish Singh
 *
 */
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
		this.movieList = new ArrayList<Movie>();
		this.shoppingList = new ArrayList<String>();
		this.cuteAnimalMemes = new ArrayList<String>();
		this.currentTime = LocalTime.now();	//null
		this.questions = new String [10];
		this.username = username;
		this.content = null;
		this.intro = null;
		this.currentTime = LocalTime.now();
		this.topics = new String [4];
		this.verbs = new String [4];
		this.followUps = new String [4];	
		
		buildVerbs();
		buildTopics();
		buildFollowUps();
		buildMovieList();
		buildShoppingList();
		buildQuestions();
	}
	
	/**
	 * Inputs different verbs to be used into the indexes of the verb list
	 */
	private void buildVerbs()
	{
		verbs[0] = "like";
		verbs[1] = "dislike";
		verbs[2] = "am ambivalent about";
		verbs[3] = "am thinking about";
	}
	/**
	 * Adds topics to the topics list for the Chatbot to use
	 */
	private void buildTopics()
	{
		
	}
	/**
	 * Adds follow ups to the followups list to be used by the chatbot
	 */
	private void buildFollowUps()
	{
		
	}
	
	/**
	 * Adds at least 6 different movies with their information into movieList
	 */
	private void buildMovieList()	//adds movie title and information to the ArrayList as well as to be used in Movie.java
	{
		movieList.add(new Movie ("Spiderman", "Fantasy/Science Fiction", "PG-13", "92%", 133, LocalDate.of(2017, 06, 28), 4));
		movieList.add(new Movie ("Hidden Figures", "Drama/Histroy", "PG", "92%", 127, LocalDate.of(2016, 12, 25), 4.0));
		movieList.add(new Movie ("March of the Penguins","Documentary", "G", "94%", 86, LocalDate.of(2005, 06, 24), 4.0));
		movieList.add(new Movie ("National Treasure", "Thriller", "PG", "88%", 131, LocalDate.of(2004, 11, 19), 3.5));
		movieList.add(new Movie ("Doctor Strange", "Fantasy/Science Fiction", "PG-13", "92%", 115, LocalDate.of(2017, 06, 20), 4));
		movieList.add(new Movie ("Murder on the Orient Express", "Mystery/Crime", "PG-13", "83%", 114, LocalDate.of(2017, 11, 10), 3));
	}
	
	/**
	 * Adds different items to the shoppingList List
	 */
	private void buildShoppingList()
	{
		shoppingList.add("snacks");
		shoppingList.add("veggies");
		shoppingList.add("protein");
//		shoppingList.add("slug bait");	//WILL FAIL A TEST LATER
		shoppingList.add("gross things");
		shoppingList.add("ice cream");
		shoppingList.add("sausages");
		shoppingList.add("noodles");
		shoppingList.add("cheese");
		shoppingList.add("clothes");
		shoppingList.add("shoes");
		shoppingList.add("A Life");
	}
	
	private void buildCuteAnimals()
	{
		
	}
	
	/**
	 * Adds different questions for the questions array for each index
	 */
	private void buildQuestions()	//creates and adds new strings (questions for the user) into the questions array
	{
		questions[0] = "What is your name?";
		questions[1] = "What do you like to do?";
		questions[2] = "What do you hate doing?";
		questions[3] = "What is your favorite movie?";
		questions[4] = "What is your favorite pie?";
		questions[5] = "What is your favorite holiday?";
		questions[6] = "What is your favorite pet?";
		questions[7] = "What is your favorite flavor of tree?";
		questions[8] = "What is your favorite ocean current?";
		questions[9] = "What is your favorite bean type?";
	}
	
	/**
	 * Method takes the user's response and creates a new text String based on the response and returns it as displayed text.
	 * @param input - takes the user's response
	 * @return The text combined from chatbotResponse and buildChatbotResponse() is returned.
	 */
	public String processConversation(String input)		//creates a method response for the chatbot to use that takes the user's input and uses it to create a response
	{
		String chatbotResponse = "";
		currentTime = LocalTime.now();
		chatbotResponse += currentTime.getHour() + ":" + currentTime.getMinute() + " ";
		chatbotResponse += "You said:" + "\n" + input + "\n";
		
		chatbotResponse += buildChatbotResponse();
		
		return chatbotResponse;
	}
	
	/**
	 * Creates a response for the chatbot by taking user input and randomized selections and connecting them together.
	 * @return the response made from user text and random verbs, topics, and questions.
	 */
	private String buildChatbotResponse()		//creates a response for the chatbot that randomly selects a verb, topic, and question to ask the user
	{
		String response = "I ";
		int random = (int) (Math.random() * verbs.length);
		
		response += verbs[random];
		
		random = (int) (Math.random() * topics.length);
		response += " " +topics[random] + ".\n";
		
		random = (int) (Math.random() * questions.length);
		response += questions[random];
		
		random = (int) (Math.random() * 2);
		
		if (random % 2 == 0) //almost 50% chance, checks if it is divisible by 2 between 1 & 2 and takes the remainder
		{
			random = (int) (Math.random()* movieList.size());
			response += "\n" + movieList.get(random).getTitle() + " is a great movie!";	//gets a random movie title and displays it with a statement
		}
		
		int followup = (int) (Math.random() * 5);
		switch (followup)
		{
		case 0:
			response += followUps[0] + "\n";
			break;
		case 3:
			response += followUps[1] + "\n";
		case 1:
			response += followUps[2]+ "\n";
			break;
		default:
			response += followUps[3] + "\n";
			response += followUps[3] + "\n";
			break;
		}
		return response;
	}
	
	/**
	 * 
	 */
	public String toString()
	{
		String toString = "";
		return toString;
	}
	
	/**
	 * Checks the user's response to see if it is longer than 2 characters.
	 * @param input - User's response as a text String is the input.
	 * @return Returns true if the String is longer than 2 characters else returns false.
	 */
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
	
	/**
	 * Checker looks to see if a HTML tag is valid and returns true if so.
	 * @param input takes the user's input
	 * @return returns true or false if it is a valid HTML tag or not
	 */
	public boolean htmlTagChecker(String input)
	{
		
		//Mr. Hendrichsen's code, not all for completing the tests
		boolean containsHTML = false;
		if(input == null || !input.contains("<"))
		{
			return containsHTML;
		}
		
		 int firstOpen = input.indexOf("<");
		 int firstClose = input.indexOf(">", firstOpen);
		 int secondOpen = -9; 	//Guaranteed to be wrong
		 int secondClose = -9;
		 String tagText =  "";
		 
		 //Check bad tags
		 if(input.contains("<>") || input.indexOf("< >") > -1)
		 {
			 containsHTML = false;
		 }
		 //Check singleton
		 if(input.toUpperCase().contains("<P>") || input.toLowerCase().contains("<br>"));
		 {
			 containsHTML = true;
		 }
		 //Check Others
//		 else if(firstClose > firstOpen)
//		 {
//			 //Others
//			 tagText = input.substring(firstOpen + 1,  firstClose).toLowerCase();
//			 secondOpen = input.toLowerCase().indexOf("</" + tagText, firstClose);
//			 
//			 containsHTML = false;
//		 }
		 
		 return containsHTML;
		 
		 
		 
		
//			input.toLowerCase();		//forces all letters to become lower case so they are all the same to avoid conflicts
//			
//			if (input.contains("<") && !input.contains(">"))
//				return false;
//			
//			else if (input.contains("<>"))
//				return false;
//			//Alec Jone's code below
//			else if(input.contains("< >"))
//				return false;
//			
//			else
//			{
//				if (input.contains("</"))
//					return true;
//				
//				else if (input.contains("<P>") || input.contains("<BR>"))
//					return true;
//				
//				else
//					return false;
//			}
	}




	/**
	 * Checks the user's user name to see if it is valid or not.
	 * @param input - the user's input
	 * @return returns true if the user name passes the requirements
	 */
	public boolean userNameChecker(String input)
	{
		boolean validUsername = false;
		int symbolCount = 0;
		

		
		//Testing starts here
		
		if(input != null && input.contains(input))	// have to do != for checking null
		{
			
			//Checks for two or more "@" symbols
			for(int i = 0; i < input.length(); i++)
			{
			    if(input.charAt(i) == '@')	//"" is for a string '' is for a character
				{
					symbolCount += 1;
				}
			}
			
			if(!input.contains("@"))
			{
				validUsername = false;
			}
			else if(input.isEmpty())
			{
				validUsername = false;
			}
		
			else if(symbolCount > 1)
			{
				validUsername = false;
			}
			
			else if(!(input.charAt(0) == '@'))
			{
				validUsername = false;
			}
			else
			{
				validUsername = true;
			}
		}
		
		return validUsername;

	}
	/**
	 * Checks user's content input and checks if it's valid
	 * @param contentCheck - user's input to see if it is correct
	 * @return returns true if the content passes and is valid for the program to read
	 */
	public boolean contentChecker(String contentCheck)
	{
		return false;
	}
	/**
	 * Checks if there are valid animal memes including an otter, puppy, and kitten in the List
	 * @param input - items within the Animal Meme list
	 * @return returns true if the list contains the correct memes and is valid
	 */
	public boolean cuteAnimalMemeChecker(String input)
	{
		return false;
	}
	
	/**
	 * Looks through the shoppingList list to see if it passes the requirements.
	 * @param shoppingItem - the required shopping items
	 * @return returns true if all parameters are passed
	 */
	public boolean shoppingListChecker(String shoppingItem)	//looks through the shoppingList list to see if it contains the correct shoppingItem
	{
			if(shoppingList.contains(shoppingItem))
			{
				return true;
			}
			else
				return false;	
	}
	
	/**
	 * Checks the movie titles from the movieList to see if they are valid.
	 * @param title - titles of movie from movieList
	 * @return returns true if the movie titles are valid
	 */
	public boolean movieTitleChecker(String title)
	{
		boolean gotMovie = false;
		
		for(int i = 0; i < movieList.size(); i++) 
		{
			if(!movieList.get(i).getTitle().contains("")  && movieList.get(i).getTitle().contains(title))
			{
				gotMovie = true;
			}
		}
		
		return gotMovie;
	}
	
	/**
	 * Checks the movies' genre's to see if they are valid.
	 * @param genre - genre of the movies from movieList
	 * @return returns true if the movies' genre's pass the statements
	 */
	public boolean movieGenreChecker(String genre)
	{
		boolean gotGenre = false;
		for(int i = 0; i < movieList.size(); i++)
		{
			if(!movieList.get(i).getGenre().contains("") && movieList.get(i).getGenre().contains(genre))
			{
				gotGenre = true;
			}
			else
				gotGenre = false;
		}
		return gotGenre;
	}

	/**
	 * Checks user's input to see if they stated "quit" to terminate the program.
	 * @param exitString - user's response
	 * @return returns true if user typed "quit" (ignores case) and is not null
	 */
	public boolean quitChecker(String exitString)		//checks if the user typed in quit (casing doesn't matter) and returns true if they did AND if it isn't a null
	{
		if (exitString != (null) && exitString.equalsIgnoreCase("quit"))
		{
			return true;
		}

		return false;
	}

	/**
	 * Check's to see if user has typed something that is just random key inputs.
	 * @param sample - strings of text implied with keyboard mashing
	 * @return returns true if detects signs of keyboard mashing with certain combinations
	 */
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
		return intro;
	}
	
	public LocalTime getCurrentTime()
	{
		return currentTime;
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
