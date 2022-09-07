package com.formation.javaformation.controllers;

import com.formation.javaformation.dto.CustomerDTO;
import com.formation.javaformation.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/create")
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customer) throws URISyntaxException {
       if(customer.getAddressDTO().getZipCode()==980000){
           return ResponseEntity
                   .notFound().build();
       }
       CustomerDTO customerCreated = this.customerService.create(customer);
        return ResponseEntity
                .created(new URI("/v1/customer/find" + "customerCreated.getId()"))
                .body(null);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CustomerDTO> find(@PathVariable(value = "id", required = true) final UUID id) throws URISyntaxException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(id);
        return ResponseEntity
                .created(new URI("/v1/customer/find" + "customerCreated.getId()"))
                .body(customerDTO);
    }
}
