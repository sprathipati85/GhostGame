package com.ghostgame;

import java.util.HashMap;
import java.lang.IllegalArgumentException;
import java.util.logging.Logger;

/*
 *  This is an n-ary tree node that holds a single character.
 *  LetterNode class which uses the LoadDictionary
 */


public class LetterNode {

	private char letter;
	private HashMap<Character, LetterNode> nodes   = new HashMap();
	
	public LetterNode(String word)
	{
		if (word.length() > 0)
			letter = word.charAt(0);

		if (word.length() > 1)
			nodes.put(Character.valueOf(word.charAt(1)), new LetterNode(word.substring(1)));
	}
	

	public void addWord(String word) throws IllegalArgumentException   {
		if (word.charAt(0) == letter)
		{
			if (!nodes.isEmpty())
			{
				if (word.length() == 1)
					nodes.clear();
				else
				{
					LetterNode nextNode = (LetterNode) nodes.get(Character.valueOf(word.charAt(1)));

					if (nextNode == null)
						nodes.put(Character.valueOf(word.charAt(1)),
								new LetterNode(word.substring(1)));
					else
						nextNode.addWord(word.substring(1));
				}
			}
		}
		else
			throw new IllegalArgumentException("Invalid first letter.");
	}

	
	/*
	 *  check if the leaf node is empty
	 */
	public boolean isLeafNode()  {
		return nodes.isEmpty();
	}
	
  
	public long numberOfChildNodes() { 
		return nodes.size(); 
	}

	
	/*
	 *  get the count of node
	 */
	
	public int leafNodeCount()  {
		int leaves = 0;

		if (isLeafNode())
			leaves = 1;
		else
			for (LetterNode node : nodes.values())
				leaves += node.leafNodeCount();

		return leaves;
	}
	
	/*
	 *  get the current player 
	 */
	
	public String getPlayer(String currentWordString) {
		String currentPlayer;
		
		if (currentWordString == null || currentWordString.length() == 0) {
			return null;
		}
		
		
		if(((currentWordString.length() - 1)	% 2) == 0){
			currentPlayer = "HUMAN";
		}
		else
			currentPlayer = "COMPUTER";
		
		return currentPlayer;
	}
	
}  