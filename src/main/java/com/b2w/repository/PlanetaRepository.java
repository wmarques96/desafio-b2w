package com.b2w.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.b2w.model.Planeta;


@Repository
public interface PlanetaRepository extends MongoRepository<Planeta,ObjectId> {

		
}
