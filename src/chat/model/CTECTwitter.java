package chat.model;

import chat.controller.ChatbotController;
import chat.controller.IOController;

import twitter4j.*;	//can use this since our program won't suffer too much slowdown due to its small size
//import twitter4j.TwitterException;
//import twitter4j.TwitterFactory;
//import twitter4j.Twitter;
//import twitter4j.GeoLocation;
//import twitter4j.Paging;
//import twitter4j.Query;
//import twitter4j.QueryResult;
//import twitter4j.ResponseList;
//import twitter4j.Twitter;
//import twitter4j.Status;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import java.lang.String;

public class CTECTwitter
{
	private ChatbotController appController;
	private Twitter chatbotTwitter;
	private List<Status> searchedTweets;
	private List<String> tweetedWords;
	private long totalWordCount;		//long is roughly 9 quintillion which we may hit when analyzing lots of data
	private HashMap<String, Integer> wordsAndCount;
	
	public CTECTwitter(ChatbotController appController)
	{
		this.appController = appController;
		this.searchedTweets = new ArrayList<Status>();
		this.tweetedWords = new ArrayList<String>();
		this.chatbotTwitter = TwitterFactory.getSingleton();
		this.wordsAndCount = new HashMap<String, Integer>();
		this.totalWordCount = 0;
	}
	
	public String getMostCommonWord(String username)
	{
		String mostCommon = "";
		
		collectTweets(username);		//collects the tweets of whomever's username is put in
		turnStatusesToWords();		//gets the turns the status's of people to words
		totalWordCount = tweetedWords.size(); //Sets the amount of tweetedWords.size() into totalWordCount
		String [] boring = createIgnoredWordArray();
		removeBlanks();
		trimTheBoringWords(boring);
		generateWordCount();
		
		ArrayList<Map.Entry<String, Integer>> sorted = sortHashMap();
		
		String mostCommonWord = sorted.get(0).getKey();
		int maxWord = 0;
		
		maxWord = sorted.get(0).getValue();
		
		mostCommon = "The most common word in " + username + "'s" + searchedTweets.size() + " tweets is " + mostCommonWord + ", and it was used " + maxWord + " times.\nThis is "
				+ (DecimalFormat.getPercentInstance().format(((double) maxWord)/totalWordCount)) + " of total words: " + totalWordCount + " and is " +
				((DecimalFormat.getPercentInstance().format(((double) maxWord)/wordsAndCount.size())) + " of the unique words: " + wordsAndCount.size());
				
		mostCommon += "\n\n" + sortedWords();
		
		return mostCommon;
	}
	
	private String sortedWords()
	{
		String allWords = "";
		String [] words = new String [wordsAndCount.size()];
		ArrayList<String> wordList = new ArrayList<String>(wordsAndCount.keySet());
		
		for(int index = 0; index < wordsAndCount.size(); index++)
		{
			words[index] = wordList.get(index);
		}
		for(int index = 0; index < words.length - 1; index++)
		{
			int maxIndex = index;
			
			for(int inner = index + 1; inner < words.length; inner++)
			{
				if(words[inner].compareTo(words[maxIndex]) > 0)
				{
					maxIndex = inner;
				}
			}
			
			String tempMax = words[maxIndex];	//a temp variable so it can be used as a swap
			words[maxIndex] = words[index];
			words[index] = tempMax;
		}
		
		for(String word : words)
		{
			allWords += word + ", ";
		}
		
		return allWords;
	}
	
	private void collectTweets(String username)
	{
		searchedTweets.clear();		//clears them out so they don't mix tweet results from multiple users
		tweetedWords.clear();		//clears out the number so it won't mix
		
		Paging statusPage = new Paging(1, 100);
		int page = 1;
		long lastID = Long.MAX_VALUE;		//
		
		while(page <= 10)
		{
			statusPage.setPage(page);
			try		//because we ccan't guarantee network access, username is correct, twitter isn't down, etc.
			{
				ResponseList<Status> listedTweets = chatbotTwitter.getUserTimeline(username, statusPage);
				for(Status current : listedTweets)	//for-each loop to go through all the tweets
				{
					if(current.getId() < lastID)		//if the Id is less than the Max value of Long (it should be)
					{
						searchedTweets.add(current);		//call would keep being called if we didn't have the if block. Guarantees we will have unique tweets
						lastID = current.getId();
					}
				}
			}
			catch(TwitterException searchTweetError)
			{
				appController.handleErrors(searchTweetError);	//sends the error
			}
			page++;	//test condition change makes the page goes up by 1
		}
	}
	
	private void turnStatusesToWords()
	{
		for(Status currentStatus : searchedTweets)
		{
			String tweetText = currentStatus.getText().toLowerCase();
			tweetText = tweetText.replace("\n",  " ");
			String [] tweetWords = tweetText.split(" ");	//split commands gives an array of string from strings and since there is a space, it will consider words to be things after spaces
			for(int index = 0; index < tweetWords.length; index++)
			{
				tweetedWords.add(removePunctuation(tweetWords[index]).trim());
			}
		}
	}
	
	private String removePunctuation(String currentString)
	{
		String punctuation = ".,'?!:;\"() {}^[]<>-";		//will end up affecting website addresses because they use some of this.
		
		String scrubbedString = "";
		for(int i = 0; i < currentString.length(); i++)
		{
			if(punctuation.indexOf(currentString.charAt(i)) == -1)
			{
				scrubbedString += currentString.charAt(i);
			}
		}
		return scrubbedString;
	}
	
	private String [] createIgnoredWordArray()
	{
		String [] boringWords;
		String fileText = IOController.loadfromFile(appController,  "commonWords.txt");	//grabs the text from the file
		int wordCount = 0;	//sets the word count to 0
		
		Scanner wordScanner = new Scanner(fileText);	//can read a string
		
		while(wordScanner.hasNextLine())	//counts and eats the scanner lines as long as there are more lines
		{
			wordScanner.nextLine();	
			wordCount++;
		}
		
		boringWords = new String [wordCount];
		wordScanner.close();		//MUST close the access point because it is programmer's responsibility
		
		//Alternative file loading method.
		//Uses the InputStream class
		//Notice the lack of try/catch
		
		wordScanner = new Scanner(this.getClass().getResourceAsStream("data/commonWords.txt"));	//turns it into an inputStream
		for(int index = 0; index < boringWords.length; index++)
		{
			boringWords[index] = wordScanner.nextLine();		//each word is put into the array
		}
		
		wordScanner.close();		//scanner is closed
		return boringWords;
	}
	
	public void sendTweet(String textToTweet)
	{
		
		try
		{
			chatbotTwitter.updateStatus(textToTweet + " @ChatbotCTEC");
		}
		catch(TwitterException tweetError)
		{
			appController.handleErrors(tweetError);
		}
		catch(Exception otherError)
		{
			appController.handleErrors(otherError);
		}
		
		
	}
	
	private void trimTheBoringWords(String [] boringWords)
	{
		for(int index = tweetedWords.size() - 1; index >= 0; index--)
		{
			for(int removeIndex = 0; removeIndex < boringWords.length; removeIndex++)	//usually always better to go backwards when removing
			{
				if(tweetedWords.get(index).equalsIgnoreCase(boringWords[removeIndex]))
				{
					tweetedWords.remove(index);
					removeIndex = boringWords.length;
				}
			}
		}
	}
	
	private void removeBlanks()
	{
		for (int index = tweetedWords.size() - 1; index >= 0; index--)	//backwards so you don't get out of bounds
		{
			if(tweetedWords.get(index).trim().length() == 0)	//spaces off of the front and back end are removed from a word
			{
				tweetedWords.remove(index);
			}
		}
	}
	
	private void generateWordCount()
	{
		for(String word: tweetedWords)
		{
			if(!wordsAndCount.containsKey(word.toLowerCase()))	//if it returns false
			{
				wordsAndCount.put(word.toLowerCase(), 1);	
			}
			else
			{
				wordsAndCount.replace(word.toLowerCase(), wordsAndCount.get(word.toLowerCase()) + 1);	 
			}
		}
	}
	
	private ArrayList<Map.Entry<String, Integer>> sortHashMap()	//going to be used to do sorting for us
	{
		ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(wordsAndCount.entrySet());
		entries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
		
		return entries;
	}
	
	public String analyzeTwitterForTopic(String topic)
	{
		String results = "";
		searchedTweets.clear();
		Query twitterQuery = new Query(topic);
		
		//GeoCode
		int resultMax = 750;
		double latitude = 40.5650;
		double longitude = 111.8390;
		double radius = 500;		//500 Mile radius
		
		long lastId = Long.MAX_VALUE;
		
		//twitterQuery.track(new String[] {});	//use to track certain words or keywords
		
		twitterQuery.setGeoCode(new GeoLocation(latitude, longitude), radius, Query.MILES);
		twitterQuery.setLang("en");	//sets the search results language to English
		twitterQuery.setResultType(Query.MIXED);	//Guarantees the results are a mix of popular and recent - already default without specifying
		
		ArrayList<Status> matchingTweets = new ArrayList<Status>();
		
		while(searchedTweets.size() < resultMax)
		{
			try
			{
				QueryResult resultingTweets = chatbotTwitter.search(twitterQuery);
				
				for(Status currentTweet : resultingTweets.getTweets()) 
				{
					if(currentTweet.getId() < lastId)
					{
						matchingTweets.add(currentTweet);
						lastId = currentTweet.getId();
					}
				}
			}
			catch(TwitterException error)
			{
				appController.handleErrors(error);
			}
			
			twitterQuery.setMaxId(lastId - 1);
		}
		
		results += "Talk about the search results";
		results += "Find a tweet that will pass one of the checkers in chatbot";
		
		int randomTweet = (int)(Math.random() * matchingTweets.size());
		results += matchingTweets.get(randomTweet);
		return results;
	}
}
