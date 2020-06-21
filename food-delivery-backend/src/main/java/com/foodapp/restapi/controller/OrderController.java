package com.foodapp.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.restapi.dao.OrderDAO;
import com.foodapp.restapi.model.Order;
import com.foodapp.restapi.service.SequenceGeneratorService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	SequenceGeneratorService seqGeneratorService;
	
	@PostMapping("/create")
	public Order create(@RequestBody Order newOrderObject) {
		newOrderObject.setId(seqGeneratorService.generateSequence(Order.SEQUENCE_NAME));
		return orderDAO.save(newOrderObject);
	}
	
	@GetMapping("/read")
	public List<Order> read(){
		return orderDAO.findAll();
	}
	
	@GetMapping("/read/{id}")
	public Order read(@PathVariable Long id) {
		Optional<Order> orderObj = orderDAO.findById(id);
		if(orderObj.isPresent()) {
			return orderObj.get();
		}else {
			throw new RuntimeException("Order not found with id "+id);
		}
	}
	
	@PutMapping("/update")
	public Order update(@RequestBody Order modifiedOrderObject) {
		return orderDAO.save(modifiedOrderObject);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		Optional<Order> orderObj = orderDAO.findById(id);
		if(orderObj.isPresent()) {
			orderDAO.delete(orderObj.get());
			return "Order deleted with id "+id;
		}else {
			throw new RuntimeException("Order not found for id "+id);
		}
	}
	
}
