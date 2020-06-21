package com.foodapp.restapi.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.foodapp.restapi.model.Restautrant;

@Repository
public interface RestautrantDAO extends MongoRepository<Restautrant, Long> {

}
