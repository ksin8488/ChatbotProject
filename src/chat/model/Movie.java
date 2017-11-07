package chat.model;

import java.time.LocalDate;

public class Movie
{
	private String title;
	private String genre;
	private String ratingMPAA;
	private String review;
	private int length;
	private LocalDate releaseDate;
	private double starScore;
	
	public Movie(String title)
	{
		this.title = "title";					//""
		this.genre = "";					//null
		this.ratingMPAA = "";		//null
		this.review = "";				//null
		this.length = 1;				//-99
		this.releaseDate = LocalDate.of(2017, 11, 1);		//null
		this.starScore = 0.00;			//Double.NaN essentially means that it is both - infinity and + infinity all at once (Undefined)
	}

	//another Movie function to pass all the movie information into it
	public Movie(String title, String genre, String ratingMPAA, String review, int length, LocalDate releaseDate, double starScore)
	{
		this.title = "title";					//""
		this.genre = "";					//null
		this.ratingMPAA = "";		//null
		this.review = "";				//null
		this.length = 1;				//-99
		this.releaseDate = LocalDate.of(2017, 11, 1);		//null
		this.starScore = 0.00;			//Double.NaN essentially means that it is both - infinity and + infinity all at once (Undefined)
	}
	
	public String getTitle()
	{
		return title;
	}

	public String getGenre()
	{
								//this.genre = "@@@@@@@";
		return genre;
	}

	public String getRatingMPAA()
	{
		return ratingMPAA;			//null
	}

	public String getReview()
	{
		return review;
	}

	public int getLength()
	{
		return length; 		//-99999999;
	}

	public LocalDate getReleaseDate()
	{
		return releaseDate;
	}

	public double getStarScore()
	{
		return starScore;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public void setRatingMPAA(String ratingMPAA)
	{
		this.ratingMPAA = ratingMPAA;
	}

	public void setReview(String review)
	{
		this.review = review;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public void setReleaseDate(LocalDate releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	public void setStarScore(double starScore)
	{
		this.starScore = starScore; 		//		this.starScore = this.starScore;
	}
	
	public String toString()
	{
		String movieFacts = title + " was made in " + releaseDate + " as part of the " +  genre + " genre. It's " + length + " minutes long and is rated as " + ratingMPAA + " with reviews having a " + review + " with a final Star rating of " + starScore + " out of 5 stars.";
		return movieFacts;			//null
	}
}
