package com.foodapp.restapi.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.foodapp.restapi.model.Order;

@Repository
public interface OrderDAO extends MongoRepository<Order, Long> {

}
