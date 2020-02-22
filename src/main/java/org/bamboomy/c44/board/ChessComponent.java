package org.bamboomy.c44.board;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ChessComponent {
	
	private String profile = "dev";
	
	public ChessComponent() {
		
		Board.staticProfile = profile;
		
		System.out.println(profile);
	}

}
