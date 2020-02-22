package org.bamboomy.c44.board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class ChessComponent {
	
	@Value("${server.port}")
	@Getter
	private String profile;
	
	public ChessComponent() {
		
		Board.staticProfile = profile;
		
		System.out.println(profile);
	}

}
