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
import java.util.ArrayList;
import java.text.DecimalFormat;

public class CTECTwitter
{
	private ChatbotController appController;
	private Twitter chatbotTwitter;
	private List<Status> searchedTweets;
	private List<String> tweetedWords;
	private long totalWordCount;		//long is roughly 9 quintillion which we may hit when analyzing lots of data
	
	public CTECTwitter(ChatbotController appController)
	{
		this.appController = appController;
		this.searchedTweets = new ArrayList<Status>();
		this.tweetedWords = new ArrayList<String>();
		this.chatbotTwitter = TwitterFactory.getSingleton();
		this.totalWordCount = 0;
	}
	
	public String getMostCommonWord(String username)
	{
		String mostCommon = "";
		
		collectTweets(username);		//collects the tweets of whomever's username is put in
		turnStatusesToWords();		//gets the turns the status's of people to words
		
		return mostCommon;
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
			String tweetText = currentStatus.getText();
			String [] tweetWords = tweetText.split(" ");
			for(int index = 0; index < tweetWords.length; index++)
			{
				tweetedWords.add(removePunctuation(tweetWords[index]).tring());
			}
		}
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
}
