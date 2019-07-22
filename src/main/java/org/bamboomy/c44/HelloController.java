package org.bamboomy.c44;

import org.bamboomy.c44.board.Board;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	private Board board = BoardController.getInstance().getBoard();
	
    @GetMapping({"/", "/hello"})
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
    	
        model.addAttribute("board", board);
        return "hello";
    }

    @GetMapping({"/newGame"})
    public String newGame(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
    	
    	BoardController.getInstance().setBoard(new Board());
    	
    	board = BoardController.getInstance().getBoard();
    	
        model.addAttribute("board", board);
        return "hello";
    }

    /*
    @PostMapping(path = "/piece", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<String> clickPiece(@RequestBody String md5) {
        System.out.println(md5);
        
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }
    */
    
}