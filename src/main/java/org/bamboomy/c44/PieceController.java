package org.bamboomy.c44;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.bamboomy.c44.board.Board;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/peace")
public class PieceController {

	@RequestMapping(value = "/{md5}/{hash}", method = RequestMethod.GET)
	public synchronized String getData(@PathVariable("md5") String md5, @PathVariable("hash") String hash) {

		System.out.println(md5);

		System.out.println(hash);

		Board board = BoardController.getInstance().getBoard(hash);

		board.click(md5);

		return "succezzz";
	}

	@RequestMapping(value = "/newGame", method = RequestMethod.GET)
	public synchronized String getData(
			@RequestParam(value = "playerHash", required = false, defaultValue = "World") final String playerHash,
			@RequestParam(value = "player2Hash", required = false, defaultValue = "World") final String player2Hash,
			@RequestParam(value = "player3Hash", required = false, defaultValue = "World") final String player3Hash,
			@RequestParam(value = "player4Hash", required = false, defaultValue = "World") final String player4Hash) {

		System.out.println("new: , " + playerHash + ", " + player2Hash + ", " + player3Hash + ", " + player4Hash);

		String time = System.currentTimeMillis() + "677";

		String md5 = null;

		time += (Math.random() * 999);

		try {

			byte[] bytesOfMessage = time.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);

			md5 = DatatypeConverter.printHexBinary(thedigest).toUpperCase();

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

			throw new RuntimeException(e);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

			throw new RuntimeException(e);
		}

		BoardController.getInstance().createBoard(md5, playerHash, player2Hash, player3Hash, player4Hash);

		return md5;
	}
}
