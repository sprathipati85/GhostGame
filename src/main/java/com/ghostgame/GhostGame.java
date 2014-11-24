package com.ghostgame;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;

public class GhostGame {

	private final static String DEFAULT_FILE_NAME = "word.lst";
	
	private LetterNode currentLetterNode;


	public GhostGame() throws IOException {
		LoadDictionary dictionary = new LoadDictionary(DEFAULT_FILE_NAME);
	    if (dictionary.size() == 0)
	      throw new RuntimeException("Unable to load dictionary from "  + DEFAULT_FILE_NAME + ".");
	}

	public boolean gameOver() {
		return currentLetterNode.isLeafNode();
	}

	public LetterNode playWithComputer(Character nextMove) {
		if (nextMove != null) {
			playHumanGame("HUMAN", nextMove);

			playComputerGame("COMPUTER");
		}

		return currentLetterNode;
	}

	private void playHumanGame(String player, Character nextMove) {
		if (gameOver()) {
			return;
		}

		if (nextMove == null) {
			throw new NotAValidPlay(MessageFormat.format("Invalid play [{0}] for player [{1}]. The resulting word [{2}] "+ 
						"does not exist in the dictionary and cannot be extended into a word ...", nextMove, player, currentLetterNode.toString() + nextMove));
		}
	}
	
	
	private void playComputerGame(String player) {
		
	}
	

  public LetterNode getCurrentLetterNode() {
			return currentLetterNode;
		}

	public static class NotAValidPlay extends IllegalArgumentException {
		private static final long serialVersionUID = 1L;

		public NotAValidPlay(String s) {
			super(s);
		}
	}
}

