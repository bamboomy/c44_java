package org.bamboomy.c44;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GameRepository extends CrudRepository<Game, Integer> {

	@Query("SELECT t FROM Game t WHERE t.hash = ?1")
	Game findByHash(String hash);
}