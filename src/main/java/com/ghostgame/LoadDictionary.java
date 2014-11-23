package com.ghostgame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/*
 *  Class that loads the data from WORD.LST into Map.  Words of four or more letters are loaded.
*/

public class LoadDictionary
{
	private static final int MIN_WORD_LENGTH = 4;

	private HashMap<Character, LetterNode> words = new HashMap();
	
	
/*
 * Load only words which has four or more letters.
*/

	private void addWord(String word)
	{
		if (word.length() >= MIN_WORD_LENGTH)
		{
			LetterNode node = (LetterNode) words.get(Character.valueOf(word.charAt(0)));

			if (node == null)
				words.put(Character.valueOf(word.charAt(0)),new LetterNode(word));
			else
				node.addWord(word);
		}
	}

	
/*
 *  Load the data from WORD.LST into Map. 
*/
		
	public LoadDictionary(String dictionary) throws IOException    {
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(dictionary));
			String word = null;
			while ((word = reader.readLine()) != null)
				addWord(word);
		}
		catch (FileNotFoundException fileNotFoundException)
		{
		}
		catch (IOException ioException)
		{
		}
		finally{
			reader.close();
		}
	}

	public int size()
	{
		int count = 0;

		for (LetterNode node : words.values())
			count += node.leafNodeCount();

		return count;
	}
} 