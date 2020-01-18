package org.bamboomy.c44;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GameResultRepository extends CrudRepository<GameResult, Integer> {

}