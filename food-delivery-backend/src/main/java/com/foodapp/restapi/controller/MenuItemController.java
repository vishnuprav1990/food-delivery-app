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

import com.foodapp.restapi.dao.MenuItemDAO;
import com.foodapp.restapi.dao.MenuItemDAO;
import com.foodapp.restapi.model.MenuItem;
import com.foodapp.restapi.service.SequenceGeneratorService;

@RestController
@RequestMapping("/api/menuitem")
public class MenuItemController {

	@Autowired
	MenuItemDAO menuItemDAO;
	
	@Autowired
	SequenceGeneratorService seqGeneratorService;
	
	@PostMapping("/create")
	public MenuItem create(@RequestBody MenuItem newMenuItemObject) {
		newMenuItemObject.setId(seqGeneratorService.generateSequence(MenuItem.SEQUENCE_NAME));
		return menuItemDAO.save(newMenuItemObject);
	}
	
	@GetMapping("/read")
	public List<MenuItem> read(){
		return menuItemDAO.findAll();
	}
	
	@GetMapping("/read/{id}")
	public MenuItem read(@PathVariable Long id) {
		Optional<MenuItem> menuItemObj = menuItemDAO.findById(id);
		if(menuItemObj.isPresent()) {
			return menuItemObj.get();
		}else {
			throw new RuntimeException("MenuItem not found with id "+id);
		}
	}
	
	@PutMapping("/update")
	public MenuItem update(@RequestBody MenuItem modifiedMenuItemObject) {
		return menuItemDAO.save(modifiedMenuItemObject);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		Optional<MenuItem> menuItemObj = menuItemDAO.findById(id);
		if(menuItemObj.isPresent()) {
			menuItemDAO.delete(menuItemObj.get());
			return "MenuItem deleted with id "+id;
		}else {
			throw new RuntimeException("MenuItem not found for id "+id);
		}
	}
	
}
