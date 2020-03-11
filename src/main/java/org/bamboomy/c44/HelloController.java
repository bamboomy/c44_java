package org.bamboomy.c44;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

import org.bamboomy.c44.board.Board;
import org.bamboomy.c44.board.Color;
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

	private SecureRandom secureRandom = new SecureRandom("50DE8CAA507BA8E8953CEEEC9570F88D".getBytes());

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

		board.setColorTaken(user);

		Iterable<ColorsTaken> userIterable = colorsTakenRepository.findByGameHash(gameHash);

		Iterable<ColorsTaken> dubiousIterable = colorsTakenRepository.findByGameHash(gameHash);

		board.setColorsTaken(userIterable);

		for (ColorsTaken userColor : userIterable) {

			if (userColor.getColor().equalsIgnoreCase("red")) {

				board.setRedName(detectBot(userColor.getName(), Color.RED, board, dubiousIterable, user.getColor(), 0));

			} else if (userColor.getColor().equalsIgnoreCase("green")) {

				board.setGreenName(
						detectBot(userColor.getName(), Color.GREEN, board, dubiousIterable, user.getColor(), 2));

			} else if (userColor.getColor().equalsIgnoreCase("blue")) {

				board.setBlueName(
						detectBot(userColor.getName(), Color.BLUE, board, dubiousIterable, user.getColor(), 3));

			} else if (userColor.getColor().equalsIgnoreCase("yellow")) {

				board.setYellowName(
						detectBot(userColor.getName(), Color.YELLOW, board, dubiousIterable, user.getColor(), 1));
			}
		}

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

		if (user.getColor().equalsIgnoreCase("chat")) {

			model.addAttribute("board", board);
			model.addAttribute("user", user);

			return "judge";
		}

		board.setTimestamp(Color.getByName(user.getColor()).getSeq(), System.currentTimeMillis());

		if (board.isClockRunning()) {

			board.updateTime();
		}

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

	private String detectBot(String name, Color color, Board board, Iterable<ColorsTaken> dubiousIterable,
			String userColor, int index) {

		if (name.equalsIgnoreCase("Random85247")) {

			board.setRandom(color.getSeq());

			board.setTimestamp(color.getSeq(), System.currentTimeMillis());

			return "Random";
		}

		if (name.equalsIgnoreCase("Dubious85247")) {

			board.setTimestamp(color.getSeq(), System.currentTimeMillis());

			board.setDubious(color.getSeq());

			String dubiousName = "Dubious: ";

			for (ColorsTaken otherName : dubiousIterable) {

				if (board.getDubious().getCurrent().equalsIgnoreCase(otherName.getColor())) {

					dubiousName += otherName.getName();
				}
			}

			return dubiousName;
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

		time += (secureRandom.nextDouble() * 458712);

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