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
	public GameResultRepository gameResultRepository;

	private static SecureRandom secureRandom = new SecureRandom("50DE8CAA507BA8E8953CEEEC9570F88D".getBytes());

	@GetMapping({ "/" })
	public String hello(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "World") final String hash) {

		System.out.println(hash);
		
		Board.gameResultRepository = gameResultRepository;

		ColorsTaken user = colorsTakenRepository.findByHash(hash);

		if (user == null) {

			return "negative";
		}

		String gameHash = user.getGame();

		Board board = BoardController.getInstance().getBoard(gameHash);

		Game game = gameRepository.findByHash(gameHash);

		board.setGameName(game.getSentence());

		board.setColorTaken(user);

		Iterable<ColorsTaken> userIterable = colorsTakenRepository.findByGameHash(gameHash);

		Iterable<ColorsTaken> dubiousIterable = colorsTakenRepository.findByGameHash(gameHash);

		board.setColorsTaken(userIterable);

		for (ColorsTaken userColor : userIterable) {

			if (userColor.getColor().equalsIgnoreCase("red")) {

				board.setRedName(detectBot(userColor.getName(), board, dubiousIterable, userColor));

			} else if (userColor.getColor().equalsIgnoreCase("green")) {

				board.setGreenName(detectBot(userColor.getName(), board, dubiousIterable, userColor));

			} else if (userColor.getColor().equalsIgnoreCase("blue")) {

				board.setBlueName(detectBot(userColor.getName(), board, dubiousIterable, userColor));

			} else if (userColor.getColor().equalsIgnoreCase("yellow")) {

				board.setYellowName(detectBot(userColor.getName(), board, dubiousIterable, userColor));
			}
		}

		model.addAttribute("board", board);
		model.addAttribute("user", user);

		board.syncNames();

		return "hello";
	}

	@GetMapping({ "/bots/" })
	public String bots(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "World") final String gameHash) {

		Board board = BoardController.getInstance().getBoard(gameHash);

		ColorsTaken user = new ColorsTaken();

		user.setColor(Board.BOTS);
		user.setJavaHash(getToken());

		board.setColorTaken(user);

		model.addAttribute("board", board);
		model.addAttribute("user", user);

		return "bots";
	}

	@GetMapping({ "/board/" })
	public String board(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "World") final String hash) {

		System.out.println(hash);

		Board board = BoardController.getInstance().getBoardByPlayerHash(hash);

		if (board == null) {

			return "negative";
		}

		ColorsTaken user = board.getColorsTaken(hash);

		ColorsTaken mutated = board.getUserColor(user, colorsTakenRepository.findByGameHash(user.getGame()));

		model.addAttribute("board", board);
		model.addAttribute("user", mutated);

		return "board";
	}

	@GetMapping({ "/botsBoard/" })
	public String botsBoard(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "World") final String hash) {

		System.out.println(hash);

		Board board = BoardController.getInstance().getBoardByPlayerHash(hash);

		if (board == null) {

			return "negative";
		}

		ColorsTaken user = board.getColorsTaken(hash);

		ColorsTaken mutated = board.getUserColor(user, colorsTakenRepository.findByGameHash(user.getGame()));

		model.addAttribute("board", board);
		model.addAttribute("user", mutated);

		return "botsBoard";
	}

	@GetMapping({ "/judge/" })
	public String judge(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "World") final String hash) {

		Board board = BoardController.getInstance().getBoardByPlayerHash(hash);

		if (board == null) {

			return "negative";
		}

		ColorsTaken user = board.getColorsTaken(hash);

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

		synchronized (board) {

			if (board.isNewDead()) {

				GameResult result = new GameResult();

				result.setGame(user.getGame());
				result.setPlayer(hash);
				result.setToken(getToken());

				result.setResult(board.getLastResult());

				gameResultRepository.save(result);

				board.next();
			}
		}

		model.addAttribute("board", board);
		model.addAttribute("user", user);

		return "judge";
	}

	private String detectBot(String name, Board board, Iterable<ColorsTaken> dubiousIterable, ColorsTaken user) {

		int seq = Color.getByName(user.getColor()).getSeq();

		if (name.equalsIgnoreCase("Random85247")) {

			user.setName("Random");

			board.setRandom(seq, user);

			board.setTimestamp(seq, System.currentTimeMillis());

			return "Random";
		}

		if (name.equalsIgnoreCase("Dubious85247")) {

			board.setTimestamp(seq, System.currentTimeMillis());

			user.setName("Dubious");

			board.setDubious(seq, user);

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

		Board board = BoardController.getInstance().getBoardByPlayerHash(hash);

		if (board == null) {

			return "negative";
		}

		ColorsTaken user = board.getColorsTaken(hash);

		board.resign(user.getColor());

		return "ok";
	}

	public static String getToken() {

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

	@GetMapping({ "/stats/" })
	public String stats(Model model) {

		model.addAttribute("GAMEZ", BoardController.GAMEZ);

		return "stats";
	}
}
