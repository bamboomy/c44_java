package org.bamboomy.c44;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class GameResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String game;
	
	private String player;
	
	private String token;
	
	private String result;

}
