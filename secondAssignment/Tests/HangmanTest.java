package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Random;

import org.junit.jupiter.api.Test;

class HangmanTest {

	@Test
	void testReadingDectionary() throws IOException 
	{
		Classes.Hangman testing = new Classes.Hangman();
		String[] dictionary = testing.readingDectionary("dictionary.txt", 10000);
		assertNotNull(dictionary);
	}

	@Test
	void testSelectRandomSecretWord() throws IOException {
		Classes.Hangman testing = new Classes.Hangman();
		String[] dictionary = testing.readingDectionary("dictionary.txt", 10000);
		testing.setDictionary(dictionary);
		String tst = testing.selectRandomSecretWord();
		assertNotNull(tst);
	}

	@Test
	void testGuess() throws Exception 
	{
		Classes.Hangman testing = new Classes.Hangman();
		String[] dictionary = testing.readingDectionary("dictionary.txt", 10000);
		testing.setDictionary(dictionary);
		testing.setMaxWrongGuesses(3);
		String word = testing.selectRandomSecretWord();
		// Test for selectRandomSecretWord function
		// The function should not return null
		assertNotNull(word);
		Random r = new Random();
		Character c = word.charAt(Math.abs(r.nextInt()%word.length()));
		assertNotNull(testing.guess(c));
		
		// Check For loosing
		// The loop will find a char that does not belong to the secret word
		// After guessing a wrong character three consecutive time -As set in setMaxWrongGuesses-
		// The function should return null as indication for max wrong guesses
		for(int i = 0; i < 2; i++)
		{
			while(word.contains(c.toString()))
			{
				c = (char)(Math.abs(r.nextInt())%26 + (int)'a');
			}
			testing.guess(c);
		}
		while(word.contains(c.toString()))
		{
			c = (char)(Math.abs(r.nextInt())%26 + (int)'a');
		}
		assertNull(testing.guess(c));
		// Check for wining
		// After guessing each character in the secret word
		// Guess should return a the secret word without any dashes
		// Then temp will become equal to word
		word = testing.selectRandomSecretWord();
		String temp = "";
		for(int i = 0; i < word.length(); i++)
		{
			temp = testing.guess(word.charAt(i));
		}
		assertEquals(temp, word);
	}
	
}
