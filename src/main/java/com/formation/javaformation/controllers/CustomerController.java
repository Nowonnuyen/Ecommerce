package com.formation.javaformation.controllers;

import com.formation.javaformation.dto.CustomerDTO;
import com.formation.javaformation.entities.Customer;
import com.formation.javaformation.exceptions.CustomerRegistrationException;
import com.formation.javaformation.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
	private final CustomerService customerService;
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	@PostMapping("/create")
	public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {
		Customer customer = customerService.findUsername(customerDTO.getUserName());
		CustomerDTO customerCreated = null;
		if(customer==null) {
			try { //try catch 
				customerCreated = this.customerService.create(customerDTO);
			} catch (CustomerRegistrationException e) {
				// TODO Auto-generated catch block
				return ResponseEntity //ResponseEntity represents the whole HTTP response: status code, headers, and body
						.badRequest()
						.header("X-Creation_Failed", "Customer creation failed")
						.body(customerCreated); //en parametre customerCreate instantication de CustomerDTP ligne 23 donc pojo de customerDTO en body?

			}
//séparer le create et le update @PUTMAPPING(‘’/update/{id}/) public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @RequestBody CustomerDTO customer)
			//faire une app angular avec formulaire d'enregistrement dans le quel on saisi les données et lorsqu'on fait envoyer ca communique avec l'app springboot
		}else {
			try {
				customerCreated = this.customerService.update(customer, customerDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block

				return ResponseEntity //ResponseEntity represents the whole HTTP response: status code, headers, and body
						.badRequest()
						.header("X-Update_Failed", "Customer update failed")
						.body(customerCreated);
			}

		}
		return ResponseEntity //ResponseEntity represents the whole HTTP response: status code, headers, and body
				.created(new URI("/v1/customer/find" + "customerCreated.getId()")) //statut 201 tout c bien passé
				.body(customerCreated); //en parametre customerCreate instantication de CustomerDTP ligne 23 donc pojo de customerDTO en body?


	}
	
	@PutMapping("/update/{username}") 
	public ResponseEntity<CustomerDTO> update(@PathVariable(value = "username", required = true) final  String username, @RequestBody CustomerDTO customer) {
	
		return null;
	}
	
	





	@GetMapping("/find/{id}")
	public ResponseEntity<CustomerDTO> find(@PathVariable(value = "id", required = true) final long id) throws URISyntaxException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(id); // pk set et pas get?
		return ResponseEntity
				.created(new URI("/v1/customer/find" + "customerCreated.getId()"))
				.body(customerDTO); //je crois que en parametre c le pojo de customer DTO



	}
	
	@GetMapping("/findall")
	public  List<CustomerDTO> findall()throws URISyntaxException {
		List<CustomerDTO> customersDTO = customerService.findall();
		return customerService.findall();
			

	}


}

