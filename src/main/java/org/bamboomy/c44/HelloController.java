package org.bamboomy.c44;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.bamboomy.c44.board.Board;
import org.bamboomy.c44.board.Player;
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
		
		ColorsTaken mutated = board.getUserColor(user, colorsTakenRepository.findByGameHash(gameHash));

		model.addAttribute("board", board);
		model.addAttribute("user", mutated);

		return "board";
	}

	@GetMapping({ "/judge/" })
	public String judge(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "World") final String hash) {

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

					board.setRedName(detectBot(userColor.getName(), Player.RED, board));
				}

			} else if (userColor.getColor().equalsIgnoreCase("green")) {

				if (userColor.getColor().equalsIgnoreCase(user.getColor())) {

					board.setGreenName("You");

				} else {

					board.setGreenName(detectBot(userColor.getName(), Player.GREEN, board));
				}

			} else if (userColor.getColor().equalsIgnoreCase("blue")) {

				if (userColor.getColor().equalsIgnoreCase(user.getColor())) {

					board.setBlueName("You");

				} else {

					board.setBlueName(detectBot(userColor.getName(), Player.BLUE, board));
				}

			} else if (userColor.getColor().equalsIgnoreCase("yellow")) {

				if (userColor.getColor().equalsIgnoreCase(user.getColor())) {

					board.setYellowName("You");

				} else {

					board.setYellowName(detectBot(userColor.getName(), Player.YELLOW, board));
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
			
			board.next();
		}

		model.addAttribute("board", board);
		model.addAttribute("user", user);

		return "judge";
	}

	private String detectBot(String name, int color, Board board) {

		if (name.equalsIgnoreCase("Random85247")) {
			
			board.setRandom(color);

			return "Random";
		}

		if (name.equalsIgnoreCase("Dubious85247")) {
			
			board.setDubious(color);

			return "Dubious";
		}

		return name;
	}

	@GetMapping({ "/resign/" })
	public String resign(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "World") final String hash) {

		System.out.println(hash + "calling resign...");

		ColorsTaken user = colorsTakenRepository.findByHash(hash);

		if (user == null) {

			return "negative";
		}

		String gameHash = user.getGame();

		Board board = BoardController.getInstance().getBoard(gameHash);

		board.resign(user.getColor());

		return "ok";
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