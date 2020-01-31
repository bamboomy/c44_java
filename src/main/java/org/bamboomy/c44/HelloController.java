package org.bamboomy.c44;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.bamboomy.c44.board.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private ColorsTakenRepository colorsTakenRepository;

	@Autowired
	private GameResultRepository gameResultRepository;

	@GetMapping({ "/" })
	public String hello(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "World") final String hash) {

		System.out.println(hash);

		ColorsTaken user = colorsTakenRepository.findByHash(hash);

		if (user == null) {

			return "negative";
		}

		String gameHash = user.getGame();

		Board board = BoardController.getInstance().getBoard(gameHash);

		Game game = gameRepository.findByHash(gameHash);

		board.setGameName(game.getSentence());

		board.setPlayerHash(hash);

		model.addAttribute("board", board);

		board.syncNames();

		return "hello";
	}

	@GetMapping({ "/board/" })
	public String board(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "World") final String hash) {

		System.out.println(hash);

		ColorsTaken user = colorsTakenRepository.findByHash(hash);

		if (user == null) {

			return "negative";
		}

		String gameHash = user.getGame();

		Board board = BoardController.getInstance().getBoard(gameHash);

		model.addAttribute("board", board);
		model.addAttribute("user", user);

		return "board";
	}

	@GetMapping({ "/judge/" })
	public String judge(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "World") final String hash) {

		System.out.println(hash);
		
		ColorsTaken user = colorsTakenRepository.findByHash(hash);

		if (user == null) {

			return "negative";
		}

		String gameHash = user.getGame();

		Board board = BoardController.getInstance().getBoard(gameHash);

		board.getLock().lock();

		Iterable<ColorsTaken> userIterable = colorsTakenRepository.findByGameHash(gameHash);

		for (ColorsTaken userColor : userIterable) {

			if (userColor.getColor().equalsIgnoreCase("red")) {

				if (userColor.getColor().equalsIgnoreCase(user.getColor())) {

					board.setRedName("You");

				} else {

					board.setRedName(userColor.getName());
				}

			} else if (userColor.getColor().equalsIgnoreCase("green")) {

				if (userColor.getColor().equalsIgnoreCase(user.getColor())) {

					board.setGreenName("You");

				} else {

					board.setGreenName(userColor.getName());
				}

			} else if (userColor.getColor().equalsIgnoreCase("blue")) {

				if (userColor.getColor().equalsIgnoreCase(user.getColor())) {

					board.setBlueName("You");

				} else {

					board.setBlueName(userColor.getName());
				}

			} else if (userColor.getColor().equalsIgnoreCase("yellow")) {

				if (userColor.getColor().equalsIgnoreCase(user.getColor())) {

					board.setYellowName("You");

				} else {

					board.setYellowName(userColor.getName());
				}
			}
		}

		board.updateTime();

		if (board.isNewDead()) {

			GameResult result = new GameResult();

			result.setGame(gameHash);
			result.setPlayer(hash);
			result.setToken(getToken());

			gameResultRepository.save(result);
		}

		model.addAttribute("board", board);
		model.addAttribute("user", user);

		return "judge";
	}

	private String getToken() {

		String time = System.currentTimeMillis() + "6";

		time += (Math.random() * 999);

		try {

			byte[] bytesOfMessage = time.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);

			return DatatypeConverter.printHexBinary(thedigest).toUpperCase();

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

			throw new RuntimeException(e);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

			throw new RuntimeException(e);
		}
	}
}