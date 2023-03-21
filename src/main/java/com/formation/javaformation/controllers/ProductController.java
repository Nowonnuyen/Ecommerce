package com.formation.javaformation.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.formation.javaformation.dto.ProductDTO;
import com.formation.javaformation.exceptions.CategoryNotFoundException;
import com.formation.javaformation.services.ProductService;
import org.slf4j.Logger;



@RestController
@RequestMapping("/v1/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	private final ProductService productService;
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	
	public ProductController(ProductService productService ) {
		this.productService = productService;
	}


	@PostMapping("/create")
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) throws URISyntaxException {

		try {
			return ResponseEntity //ResponseEntity represents the whole HTTP response: status code, headers, and body
					.created(new URI("/v1/product/find" + "productCreated.getId()")) 
					.body(productService.create(productDTO));
		}  catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity //ResponseEntity represents the whole HTTP response: status code, headers, and body
					.badRequest()
					.header("X-Creation_Failed", "Category creation failed")
					.body(null); //en parametre customerCreate instantication de CustomerDTO ligne 23 donc pojo de customerDTO en body?
		}
	}

	@PutMapping("/update/{id}") 
	public ResponseEntity<ProductDTO> update(@PathVariable(value = "id", required = true) final  long id, @RequestBody ProductDTO product) throws URISyntaxException, CategoryNotFoundException {

		if (productService.findById(id) == null) {

			return ResponseEntity //ResponseEntity represents the whole HTTP response: status code, headers, and body
					.badRequest()
					.header("X-Creation_Failed", "Category creation failed")
					.body(null); //en parametre customerCreate instantication de CustomerDTO ligne 23 donc pojo de customerDTO en body?

		} else {
			product.setId(id);
			return ResponseEntity //ResponseEntity represents the whole HTTP response: status code, headers, and body
					.created(new URI("/v1/product/find" + "productCreated.getId()")) 
					.body(productService.create(product));

		}


	}



	@GetMapping("/findall")
	public List<ProductDTO> findall() {
		return productService.findall();
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<ProductDTO> find(@PathVariable(value = "id", required = true) final long id) throws URISyntaxException {
		ProductDTO productDTO = productService.findById(id);

		return ResponseEntity
				.created(new URI("/v1/product/find" + "productCreated.getId()"))
				.body(productDTO); //je crois que en parametre c le pojo de customer DTO


	}

	@DeleteMapping("/delete/{id}") 
	public ResponseEntity<Void> delete(@PathVariable(value = "id", required = true) final long id) throws URISyntaxException {
		try {
			productService.delete(id);

		} catch (Exception ex)  {
			logger.warn("Element not found");
			

		} 
		return  ResponseEntity.noContent().build();




	}
}
