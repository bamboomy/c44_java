package org.bamboomy.c44;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ColorsTakenRepository extends CrudRepository<ColorsTaken, Integer> {

	@Query("SELECT t FROM ColorsTaken t WHERE t.javaHash = ?1")
	ColorsTaken findByHash(String hash);
	
	@Query("SELECT t FROM ColorsTaken t WHERE t.game = ?1")
	Iterable<ColorsTaken> findByGameHash(String hash);
}