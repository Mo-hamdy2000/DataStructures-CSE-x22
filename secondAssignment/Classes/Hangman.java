package Classes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import Interface.IHangman;
public class Hangman implements IHangman
{
	private String[] dicWords;
	public int wrongGuesses;
	private int maxGuesses = 10;
	private String secretWord;
	private String playingWord;
	
	public String[] readingDectionary(String path, int wordsNum) throws IOException
	{
		String[] words = new String[wordsNum];
		BufferedReader br = new BufferedReader(new FileReader(path)); 
		String st;
		int counter = 0;
		// Reading each line in the file and store the word in the array
		while ((st = br.readLine()) != null)
		{
			words[counter++] = st;
		}
		br.close();
		return words;
	}
	
	public void setDictionary(String[] words) 
	{
		// Setting a value for the private member dicWords
		dicWords = words;
	}

	public String selectRandomSecretWord() {
		Random r = new Random();
		// Select a word from random index of dictionary array
		secretWord = dicWords[Math.abs(r.nextInt()%dicWords.length)];
		wrongGuesses = 0;
		playingWord = "";
		// Forming a playing word for the user
		// Initially it contains dashes equal to secret word length
		for(int i = 0; i < secretWord.length(); i++)
		{
			playingWord += "-";
		}
		// Return the randomly selected word
		return secretWord;
	}
	
	public String guess(Character c) throws Exception 
	{
		String temp = "";
		if(c == null)
		{
			return null;
		}
		// Check for buggy secret word
		if(!secretWord.matches("^[a-zA-Z]*$"))
		{
			throw new Exception("Buggy SecretWord!!");
		}
		// Check is the passed character is already in the playing word
		// Then you already have guessed it before.
		if(playingWord.toLowerCase().contains(c.toString().toLowerCase()))
		{
			System.out.println("You have already guessed it before!!");
			return playingWord;
		}
		// Check if the secret word contains the guessed character
		// Then the user has a right guess
		// The loop will iterate to replace dashes in the playing word 
		// With the guessed character in its place
		if(secretWord.toLowerCase().contains(c.toString().toLowerCase()))
		{
			for(int i = 0; i < secretWord.length(); i++)
			{
				if(Character.toLowerCase(c) == secretWord.toLowerCase().charAt(i))
				{
					temp += c;
				}
				else
				{
					temp+=playingWord.charAt(i);
				}
			}
			playingWord = temp;
			System.out.println("Good Guess!!");
			if(!playingWord.contains("-"))
			{
				System.out.println("You have Won!!");
			}
		}
		// If the secret word does not contain the guessed character
		// Then the user has wrong guess
		else
		{
			wrongGuesses++;
			System.out.println("Wrong Guess!!");
			// Check if the user reached max number of wrong guesses
			if(wrongGuesses >= maxGuesses)
			{
				System.out.println("You have lost:(");
				return null;
			}
		}
		return playingWord;
	}
	
	// Set value private member maxGuesses 
	public void setMaxWrongGuesses(Integer max) 
	{
		if(max != null && max > 0)
		{
			maxGuesses = max;
		}
		else
		{
			maxGuesses = 1;
		}
		
	}

}
