package com.formation.javaformation.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.javaformation.dto.CategoryDTO;
import com.formation.javaformation.services.CategoryService;

@RestController
@RequestMapping("/v1/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
	private final CategoryService categoryService;
	public CategoryController(CategoryService categoryService ) {
		this.categoryService = categoryService;
	}


	@PostMapping("/create")
	public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) throws URISyntaxException {

		return ResponseEntity //ResponseEntity represents the whole HTTP response: status code, headers, and body
				.created(new URI("/v1/category/find" + "categoryCreated.getId()")) 
				.body(categoryService.create(categoryDTO));
	}

	@GetMapping("/findall")
	public List<CategoryDTO> findall() {
		return categoryService.findall();
	}


}
