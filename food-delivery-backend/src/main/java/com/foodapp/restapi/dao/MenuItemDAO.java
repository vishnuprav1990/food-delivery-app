package com.foodapp.restapi.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.foodapp.restapi.model.MenuItem;

@Repository
public interface MenuItemDAO extends MongoRepository<MenuItem, Long> {

}
