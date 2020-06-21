package com.foodapp.restapi.controller;

import java.util.ArrayList;
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
import com.foodapp.restapi.dao.RestautrantDAO;
import com.foodapp.restapi.model.MenuItem;
import com.foodapp.restapi.model.Restautrant;
import com.foodapp.restapi.service.SequenceGeneratorService;

@RestController
@RequestMapping("/api/restautrant")
public class RestautrantController {

	@Autowired
	RestautrantDAO restautrantDAO;

	@Autowired
	MenuItemDAO menuItemDAO;

	@Autowired
	SequenceGeneratorService seqGeneratorService;

	@PostMapping("/create")
	public Restautrant create(@RequestBody Restautrant newRestautrantObject) {
		newRestautrantObject.setId(seqGeneratorService.generateSequence(Restautrant.SEQUENCE_NAME));
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		if (newRestautrantObject.getMenuitems() != null && !newRestautrantObject.getMenuitems().isEmpty()) {
			newRestautrantObject.getMenuitems().forEach(item -> {
				item.setId(seqGeneratorService.generateSequence(item.SEQUENCE_NAME));
				menuItems.add(menuItemDAO.save(item));
			});
		}
	Restautrant restautrant = restautrantDAO.save(newRestautrantObject);
		if (!menuItems.isEmpty()) {
			menuItems.forEach(item -> {
				item.setRestId(restautrant.getId());
				menuItemDAO.save(item);
			});
		}
		return restautrant;
	}

	@GetMapping("/read")
	public List<Restautrant> read() {
		return restautrantDAO.findAll();
	}

	@GetMapping("/read/{id}")
	public Restautrant read(@PathVariable Long id) {
		Optional<Restautrant> restautrantObj = restautrantDAO.findById(id);
		if (restautrantObj.isPresent()) {
			return restautrantObj.get();
		} else {
			throw new RuntimeException("Restautrant not found with id " + id);
		}
	}

	@PutMapping("/update")
	public Restautrant update(@RequestBody Restautrant modifiedRestautrantObject) {
		return restautrantDAO.save(modifiedRestautrantObject);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		Optional<Restautrant> restautrantObj = restautrantDAO.findById(id);
		if (restautrantObj.isPresent()) {
			restautrantDAO.delete(restautrantObj.get());
			return "Restautrant deleted with id " + id;
		} else {
			throw new RuntimeException("Restautrant not found for id " + id);
		}
	}

}
