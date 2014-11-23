package com.ghostgame;


import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ghostgame.GhostGame.NotAValidPlay;

/*
 *  Spring MVC Controller
 *  intercepts the request and sends the response 
 */

	@Controller 
	@RequestMapping(value = "/ghostgame")
	public class GhostGameController {

	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpSession openSession, Model model) throws IOException {

		model.addAttribute("response", new Response(getGhostGameInstance(openSession).getCurrentLetterNode().toString(), ""));
		
		return "ghostgame";
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Response post(@RequestParam(value = "playgame", required =false) Character playgame, HttpSession openSession) {
	
		GhostGame playGhostGame = null;
		try {
			playGhostGame = getGhostGameInstance(openSession);
			playGhostGame.playWithComputer(playgame);
			
			if (playGhostGame.gameOver()) {
				openSession.invalidate();
				String string = playGhostGame.getCurrentLetterNode().toString();
					return new Response(string, "Player " + playGhostGame.getCurrentLetterNode().getPlayer(string) + " lost the game. " + string + " exists in dictionary ...");
			}
			
		} catch (NotAValidPlay notAValidPlay) {
			openSession.invalidate();
			return new Response(playGhostGame.getCurrentLetterNode().toString(), "Sorry Game Over: " + notAValidPlay.getMessage());
		} catch (IOException e) {
			openSession.invalidate();
			e.printStackTrace();
		}

		return new Response(playGhostGame.getCurrentLetterNode().toString(), "");
	}

/*
 *  get an instance of ghostgame and associate a session
 */

	private GhostGame getGhostGameInstance(HttpSession openSession) throws IOException {
		GhostGame ghostGame = (GhostGame) openSession.getAttribute("game");

		if (ghostGame == null) {
			ghostGame = new GhostGame();
			openSession.setAttribute("game", ghostGame);
		}

		return ghostGame;
	}
}
