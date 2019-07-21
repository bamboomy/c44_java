package org.bamboomy.c44;

import java.lang.reflect.Member;

import org.bamboomy.c44.board.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	private Board board = new Board();
	
    @GetMapping({"/", "/hello"})
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
    	
        model.addAttribute("board", board);
        return "hello";
    }
    
    @PostMapping(path = "/piece", consumes = "application/json", produces = "application/json")
    public String clickPiece(@RequestBody String md5) {
        System.out.println(md5);
        
        return "success";
    }
}