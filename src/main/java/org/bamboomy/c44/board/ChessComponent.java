package org.bamboomy.c44.board;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ChessComponent {
	
	private String profile = "dev";
	
	public ChessComponent() {
		
		Board.staticProfile = profile;
		
		Board.staticPiecePath = "/dev/peace/";
		
		Board.staticTomcatPath = "dev/tomcat";
		
		Board.staticJavaPath = "dev/java";
		
		Board.staticPHPPath = "/dev";
		
		System.out.println(profile);
	}

}
